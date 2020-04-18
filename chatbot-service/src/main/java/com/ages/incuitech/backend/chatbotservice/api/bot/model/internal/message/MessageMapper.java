package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.FacebookUser;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.Message;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.Messaging;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageMapper {
        public static List<MensagemInterna> mensagemDoUsuarioParaMensagemInterna(UserMessage message){
               return message.getEntry()
                        .stream()
                        .flatMap(entry -> entry.getMessaging()
                                .stream()
                                .map(MessageMapper::converteMensagemUsarioParaInterna)
                        ).collect(Collectors.toList());
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
