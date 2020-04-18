package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.FacebookUser;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.Messaging;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.FacebookMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.UserRecipient;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.AttachmentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.ButtonsAttachment;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.CarouselAttachmentPayload;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.TemplateMessage;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageMapper {

        public static FacebookMessage botMessageParaFacebookMessage(BotMessage botMessage, FacebookUser recipient){
                if(botMessage instanceof ButtonBotMessage) {
                        return constroiMenssagemDeBotoes((ButtonBotMessage) botMessage, recipient);
                } else if(botMessage instanceof CarrouselBotMessage){
                        return constroiMenssagemDeCarrossel((CarrouselBotMessage) botMessage, recipient);

                } else if(botMessage instanceof QuickReplyBotMessage){
                        return constroiMenssagemQuickReplyParaFacebookMenssage((QuickReplyBotMessage) botMessage,
                                recipient);

                } else{
                        return constroiTextBotMessageParaFacebookMessage((TextBotMessage) botMessage);
                }


        }

        public static List<MensagemInterna> mensagemDoUsuarioParaMensagemInterna(UserMessage message){
               return message.getEntry()
                        .stream()
                        .flatMap(entry -> entry.getMessaging()
                                .stream()
                                .map(MessageMapper::converteMensagemUsarioParaInterna)
                        ).collect(Collectors.toList());
        }

        private static FacebookMessage constroiTextBotMessageParaFacebookMessage(TextBotMessage textMessage) {
                TemplateMessage template = TemplateMessage.builder()
                        .text(textMessage.getText())
                        .build();
                return new FacebookMessage()
                        .withMessage(template);
        }

        private static FacebookMessage constroiMenssagemQuickReplyParaFacebookMenssage(QuickReplyBotMessage quickReply,
                                                                                       FacebookUser recipient) {
                TemplateMessage template = TemplateMessage.builder()
                        .quickRepliesButtons(quickReply.getQuickReplyButtons())
                        .text(quickReply.getTexto())
                        .build();
                return new FacebookMessage()
                        .withRecipient(new UserRecipient(recipient.getId()))
                        .withMessage(template);
        }

        private static FacebookMessage constroiMenssagemDeCarrossel(CarrouselBotMessage carrousel,
                                                                    FacebookUser recipient) {
                TemplateMessage template = TemplateMessage.builder()
                        .attachment(new AttachmentBotMessage(
                                new CarouselAttachmentPayload(carrousel.getElementos()))
                        ).build();
                return new FacebookMessage()
                        .withRecipient(new UserRecipient(recipient.getId()))
                        .withMessage(template);
        }

        private static FacebookMessage constroiMenssagemDeBotoes(ButtonBotMessage buttonMessage,
                                                                 FacebookUser recipient) {
                TemplateMessage template = TemplateMessage.builder()
                        .attachment(new AttachmentBotMessage(
                                new ButtonsAttachment(buttonMessage.getTexto(),
                                        buttonMessage.getBotoes()
                                )
                        )).build();
                return new FacebookMessage()
                        .withMessage(template)
                        .withRecipient(new UserRecipient(recipient.getId()));
        }

        private static MensagemInterna converteMensagemUsarioParaInterna(Messaging messaging) {
                FacebookUser facebookUser = messaging.getSender();
                MensagemInterna mensagemInterna = new MensagemInterna(facebookUser, new HashMap<>());
                if (messaging.getPostback() != null) {
                        String conteudo = messaging.getPostback().getPayload();
                        return mensagemInterna.withTipo(TipoMensagem.BOTAO).withConteudo(conteudo);
                } else if (messaging.getMessage().getQuickReply() != null) {
                        String conteudo = messaging.getMessage().getQuickReply().getPayload();
                        return mensagemInterna.withTipo(TipoMensagem.BOTAO).withConteudo(conteudo);
                } else if (!messaging.getMessage().getAttachments().isEmpty()) {
                        String conteudo = messaging.getMessage().getAttachments().get(0).getPayload().getUrl();
                        return mensagemInterna.withTipo(TipoMensagem.ANEXO).withConteudo(conteudo);
                } else {
                        String conteudo = messaging.getMessage().getText();
                        return mensagemInterna.withTipo(TipoMensagem.TEXTO).withConteudo(conteudo);
                }
        }
}
