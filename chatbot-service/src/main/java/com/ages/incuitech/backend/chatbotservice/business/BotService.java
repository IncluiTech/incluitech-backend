package com.ages.incuitech.backend.chatbotservice.business;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.service.FacebookSendService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BotService {

    private ContextManager contextManager;
    private UserService userService;
    private FacebookSendService facebookSendService;

    @Async
    public void handleMessage(List<MensagemInterna> mensagensInternas) {
        mensagensInternas.forEach(mensagem -> {
            UsuarioDaMensagem usuarioDaMensagem = userService.getUsuario(mensagem.getUsuario().getId());
            mensagem.getUsuario().setTipoUsuario(usuarioDaMensagem.getTipoUsuario());
            Map<String, Object> contexto = contextManager.getContexto(mensagem.getUsuario().getId());
            mensagem.setContexto(contexto);
            BotMessage message = processMessage(mensagem);
            facebookSendService.sendMessage();
        });
    }

    private BotMessage processMessage(MensagemInterna mensagem) {

    }
}
