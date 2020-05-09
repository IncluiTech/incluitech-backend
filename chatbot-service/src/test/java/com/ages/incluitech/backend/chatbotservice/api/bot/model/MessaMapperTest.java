package com.ages.incluitech.backend.chatbotservice.api.bot.model;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.FacebookMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.ButtonsAttachment;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.CarouselAttachmentPayload;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.LinkButton;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.PayloadButton;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MessaMapperTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
        this.mapper = new ObjectMapper();
    }

    @Test
    public void shouldTranslateUserTextMessageToTextInternalMessage() throws IOException {
        //given:
        UserMessage userMessage = getUserMessage("text_message");
        //when:
        List<MensagemInterna> mensagemInterna = MessageMapper.mensagemDoUsuarioParaMensagemInterna(userMessage);
        //then:
        Assert.assertEquals(1, mensagemInterna.size());
        Assert.assertEquals("2242", mensagemInterna.get(0).getUsuario().getId());
        Assert.assertEquals(TipoMensagem.TEXTO, mensagemInterna.get(0).getTipo());
        Assert.assertEquals("mensagem de texto", mensagemInterna.get(0).getConteudo());
    }

    @Test
    public void shouldTranslateUserButtonMessageToTextInternalMessage() throws IOException {
        //given:
        UserMessage userMessage = getUserMessage("button_message");
        //when:
        List<MensagemInterna> mensagemInterna = MessageMapper.mensagemDoUsuarioParaMensagemInterna(userMessage);
        //then:
        Assert.assertEquals(1, mensagemInterna.size());
        Assert.assertEquals("2242", mensagemInterna.get(0).getUsuario().getId());
        Assert.assertEquals(TipoMensagem.BOTAO, mensagemInterna.get(0).getTipo());
        Assert.assertEquals("BUTTON_PAYLOAD", mensagemInterna.get(0).getConteudo());
    }

    @Test
    public void shouldTranslateUserQuickReplyMessageToTextInternalMessage() throws IOException {
        //given:
        UserMessage userMessage = getUserMessage("quick_reply_message");
        //when:
        List<MensagemInterna> mensagemInterna = MessageMapper.mensagemDoUsuarioParaMensagemInterna(userMessage);
        //then:
        Assert.assertEquals(1, mensagemInterna.size());
        Assert.assertEquals("2242", mensagemInterna.get(0).getUsuario().getId());
        Assert.assertEquals(TipoMensagem.BOTAO, mensagemInterna.get(0).getTipo());
        Assert.assertEquals("QUICK_REPLY_BUTTON", mensagemInterna.get(0).getConteudo());
    }


    @Test
    public void shouldTranslateUserAttachmentMessageToTextInternalMessage() throws IOException {
        //given:
        UserMessage userMessage = getUserMessage("attachment_message");
        //when:
        List<MensagemInterna> mensagemInterna = MessageMapper.mensagemDoUsuarioParaMensagemInterna(userMessage);
        //then:
        Assert.assertEquals(1, mensagemInterna.size());
        Assert.assertEquals("2242", mensagemInterna.get(0).getUsuario().getId());
        Assert.assertEquals(TipoMensagem.ANEXO, mensagemInterna.get(0).getTipo());
        Assert.assertEquals("url", mensagemInterna.get(0).getConteudo());
    }

    @Test
    public void shouldTranslateBotTextMessageToFacebookSenderMessage() {
        //given:
        MensagemInterna mensagemInterna = buildInternalMessage();
        ComponentBotMessage botMessage = new TextComponentBotMessage("Olá eu sou a helena");
        //when:
        List<FacebookMessage> messages = MessageMapper
                .botMessageParaFacebookMessage(Collections.singletonList(botMessage), mensagemInterna);
        //then:
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals("2242", messages.get(0).getRecipient().getId());
        Assert.assertEquals("Olá eu sou a helena", messages.get(0).getMessage().getText());
    }

    @Test
    public void shouldTranslateBotButtonMessageToFacebookSenderMessage() {
        //given:
        MensagemInterna mensagemInterna = buildInternalMessage();
        List<Button> buttons = Arrays.asList(
                new PayloadButton("titulo1", "CONTEUDO_1"),
                new LinkButton("titulo2", "url")
        );
        ComponentBotMessage botMessage = new ButtonComponentBotMessage("texto ants do botão", buttons);
        //when:
        List<FacebookMessage> messages = MessageMapper
                .botMessageParaFacebookMessage(Collections.singletonList(botMessage), mensagemInterna);
        //then:
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals("2242", messages.get(0).getRecipient().getId());
        Assert.assertNotNull(messages.get(0).getMessage().getAttachment());
        Assert.assertEquals("button", messages.get(0).getMessage().getAttachment().getPayload().getTemplateType());
        ButtonsAttachment buttonsAttachment = convertAttachemntPayloadTo(ButtonsAttachment.class, messages);
        Assert.assertEquals("texto ants do botão", buttonsAttachment.getText());
        Assert.assertEquals(2, buttonsAttachment.getButtons().size());
        Assert.assertTrue(buttonsAttachment.getButtons().get(0) instanceof PayloadButton);
        Assert.assertEquals("CONTEUDO_1", ((PayloadButton) buttonsAttachment.getButtons().get(0)).getPayload());
        Assert.assertTrue(buttonsAttachment.getButtons().get(1) instanceof LinkButton);
        Assert.assertEquals("url", ((LinkButton) buttonsAttachment.getButtons().get(1)).getUrl());
    }

    @Test
    public void shouldTranslateBotQuickReplyMessageToFacebookSenderMessage() {
        //given:
        MensagemInterna mensagemInterna = buildInternalMessage();
        ComponentBotMessage botMessage = new QuickReplyComponentBotMessage("texto do quickReply",
                new QuickReplyButton("Titulo", "conteudo"),
                new QuickReplyButton("Titulo1", "conteudo1")
        );
        //when:
        List<FacebookMessage> messages = MessageMapper
                .botMessageParaFacebookMessage(Collections.singletonList(botMessage), mensagemInterna);
        //then:
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals("2242", messages.get(0).getRecipient().getId());
        Assert.assertEquals("texto do quickReply", messages.get(0).getMessage().getText());
        Assert.assertEquals(2, messages.get(0).getMessage().getQuickRepliesButtons().size());
        Assert.assertEquals("Titulo", messages.get(0).getMessage().getQuickRepliesButtons().get(0).getTitle());
        Assert.assertEquals("conteudo", messages.get(0).getMessage().getQuickRepliesButtons().get(0).getPayload());
        Assert.assertEquals("Titulo1", messages.get(0).getMessage().getQuickRepliesButtons().get(1).getTitle());
        Assert.assertEquals("conteudo1", messages.get(0).getMessage().getQuickRepliesButtons().get(1).getPayload());
    }

    @Test
    public void shouldTranslateBotCarouselMessageToFacebookSenderMessage() {
        //given:
        MensagemInterna mensagemInterna = buildInternalMessage();
        List<Button> buttonsFirstCard = Collections.singletonList(
                new PayloadButton("titulo", "CONTEUDO_1")
        );
        List<Button> buttonsSecondCard = Collections.singletonList(
                new PayloadButton("titulo", "CONTEUDO_1")
        );
        ComponentBotMessage botMessage = new CarrouselComponentBotMessage(
                Arrays.asList(new Card().withTitle("FirstCard")
                                .withSubtitle("FirstCardSubTitle").withButtons(buttonsFirstCard),
                        new Card().withTitle("SecondCard")
                                .withSubtitle("SecondCardSubTitle").withButtons(buttonsSecondCard)
                )
        );
        //when:
        List<FacebookMessage> messages = MessageMapper
                .botMessageParaFacebookMessage(Collections.singletonList(botMessage), mensagemInterna);
        //then:
        Assert.assertEquals(1, messages.size());
        Assert.assertEquals("2242", messages.get(0).getRecipient().getId());
        Assert.assertNotNull(messages.get(0).getMessage().getAttachment());
        Assert.assertEquals("generic", messages.get(0).getMessage().getAttachment().getPayload().getTemplateType());
        CarouselAttachmentPayload carouselComponentBotMessage = convertAttachemntPayloadTo(
                CarouselAttachmentPayload.class, messages);
        Assert.assertEquals(2, carouselComponentBotMessage.getElements().size());
        Assert.assertEquals("FirstCard", carouselComponentBotMessage.getElements().get(0).getTitle());
        Assert.assertEquals("FirstCardSubTitle", carouselComponentBotMessage.getElements().get(0).getSubtitle());
        Assert.assertEquals(buttonsFirstCard, carouselComponentBotMessage.getElements().get(0).getButtons());
        Assert.assertEquals("SecondCard", carouselComponentBotMessage.getElements().get(1).getTitle());
        Assert.assertEquals("SecondCardSubTitle", carouselComponentBotMessage.getElements().get(1).getSubtitle());
        Assert.assertEquals(buttonsSecondCard, carouselComponentBotMessage.getElements().get(1).getButtons());
    }

    private MensagemInterna buildInternalMessage() {
        return new MensagemInterna(
                new UsuarioDaMensagem("2242"),
                TipoMensagem.TEXTO,
                "conteudoTest"
        );
    }

    private UserMessage getUserMessage(String fileName) throws IOException {
        ClassLoader loader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(loader.getResource("usermessages/" + fileName + ".json")).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
        return mapper.readValue(content, UserMessage.class);

    }

    private <T> T convertAttachemntPayloadTo(Class<T> type, List<FacebookMessage> messages) {
        return type.cast(messages.get(0).getMessage().getAttachment().getPayload());
    }
}
