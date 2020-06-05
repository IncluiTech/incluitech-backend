package com.ages.incuitech.backend.chatbotservice.api.bot.model;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.FacebookUser;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.Messaging;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.FacebookMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.MessageType;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.UserRecipient;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.AttachmentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.ButtonsAttachment;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.CarouselAttachmentPayload;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.TemplateMessage;

import java.util.List;
import java.util.stream.Collectors;

public class MessageMapper {


    private static final String ACCOUNT_UPDATE_TAG = "ACCOUNT_UPDATE";

    public static List<FacebookMessage>     botMessageParaFacebookMessage(List<ComponentBotMessage> componentBotMessages,
                                                                      MensagemInterna mensagemInterna) {
        return componentBotMessages
                .stream()
                .map(componentBotMessage -> {
                    FacebookMessage facebookMessage = constroiFacebookMessage(componentBotMessage,
                            mensagemInterna.getUsuario());
                    if (TipoMensagem.EVENTO.equals(mensagemInterna.getTipo())) {
                        return facebookMessage.withMessageType(MessageType.MESSAGE_TAG.name())
                                .withTag(ACCOUNT_UPDATE_TAG);
                    } else return facebookMessage;
                }).collect(Collectors.toList());
    }

    public static List<MensagemInterna> mensagemDoUsuarioParaMensagemInterna(UserMessage message) {
        return message.getEntry()
                .stream()
                .flatMap(entry -> entry.getMessaging()
                        .stream()
                        .map(MessageMapper::converteMensagemUsuarioParaInterna)
                ).collect(Collectors.toList());
    }

    private static FacebookMessage constroiFacebookMessage(ComponentBotMessage componentBotMessage,
                                                           UsuarioDaMensagem usuarioDaMensagem) {
        if (componentBotMessage instanceof ButtonComponentBotMessage) {
            return constroiMenssagemDeBotoes((ButtonComponentBotMessage) componentBotMessage,
                    usuarioDaMensagem.getId());
        } else if (componentBotMessage instanceof CarrouselComponentBotMessage) {
            return constroiMenssagemDeCarrossel((CarrouselComponentBotMessage) componentBotMessage,
                    usuarioDaMensagem.getId());
        } else if (componentBotMessage instanceof QuickReplyComponentBotMessage) {
            return constroiMenssagemQuickReplyParaFacebookMenssage((QuickReplyComponentBotMessage) componentBotMessage,
                    usuarioDaMensagem.getId());
        } else {
            return constroiTextBotMessageParaFacebookMessage((TextComponentBotMessage) componentBotMessage,
                    usuarioDaMensagem.getId());
        }
    }

    private static FacebookMessage constroiTextBotMessageParaFacebookMessage(TextComponentBotMessage textMessage,
                                                                             String userId) {
        TemplateMessage template = TemplateMessage.builder()
                .text(textMessage.getText())
                .build();
        return new FacebookMessage()
                .withRecipient(new UserRecipient(userId))
                .withMessage(template);
    }

    private static FacebookMessage constroiMenssagemQuickReplyParaFacebookMenssage(QuickReplyComponentBotMessage quickReply,
                                                                                   String userId) {
        TemplateMessage template = TemplateMessage.builder()
                .quickRepliesButtons(quickReply.getQuickReplyButtons())
                .text(quickReply.getTexto())
                .build();
        return new FacebookMessage()
                .withRecipient(new UserRecipient(userId))
                .withMessage(template);
    }

    private static FacebookMessage constroiMenssagemDeCarrossel(CarrouselComponentBotMessage carrousel,
                                                                String userId) {
        TemplateMessage template = TemplateMessage.builder()
                .attachment(new AttachmentBotMessage(
                        new CarouselAttachmentPayload(carrousel.getElementos()))
                ).build();
        return new FacebookMessage()
                .withRecipient(new UserRecipient(userId))
                .withMessage(template);
    }

    private static FacebookMessage constroiMenssagemDeBotoes(ButtonComponentBotMessage buttonMessage,
                                                             String userId) {
        TemplateMessage template = TemplateMessage.builder()
                .attachment(new AttachmentBotMessage(
                        new ButtonsAttachment(buttonMessage.getTexto(),
                                buttonMessage.getBotoes()
                        )
                )).build();
        return new FacebookMessage()
                .withMessage(template)
                .withRecipient(new UserRecipient(userId));
    }

    private static MensagemInterna converteMensagemUsuarioParaInterna(Messaging messaging) {
        FacebookUser facebookUser = messaging.getSender();
        UsuarioDaMensagem usuarioDaMensagem = new UsuarioDaMensagem(facebookUser.getId());
        if (messaging.getPostback() != null) {
            String conteudo = messaging.getPostback().getPayload();
            return new MensagemInterna(usuarioDaMensagem, TipoMensagem.BOTAO, conteudo);
        } else if (messaging.getMessage().getQuickReply() != null) {
            String conteudo = messaging.getMessage().getQuickReply().getPayload();
            return new MensagemInterna(usuarioDaMensagem, TipoMensagem.BOTAO, conteudo);
        } else if (hasAttachment(messaging)) {
            String conteudo = messaging.getMessage().getAttachments().get(0).getPayload().getUrl();
            return new MensagemInterna(usuarioDaMensagem, TipoMensagem.ANEXO, conteudo);
        } else {
            String conteudo = messaging.getMessage().getText();
            return new MensagemInterna(usuarioDaMensagem, TipoMensagem.TEXTO, conteudo);
        }
    }

    public static MensagemInterna criarMensagemInternaSucessoCadastro(UsuarioDaMensagem usuarioDaMensagem) {
        return new MensagemInterna(usuarioDaMensagem, TipoMensagem.EVENTO, "", new Contexto());
    }

    private static boolean hasAttachment(Messaging messaging) {
        return messaging.getMessage().getAttachments() != null && !messaging.getMessage().getAttachments().isEmpty();
    }
}
