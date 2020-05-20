package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@AllArgsConstructor
public class NotificacaoService {

    private UserService userService;
    private BotService botService;
    private ContextManager contextManager;

    public void enviarNotificacaoSucessoCadastro(String facebookId) {
        UsuarioDaMensagem usuarioDaMensagem = userService.getUsuario(facebookId);
        MensagemInterna mensagemInterna = MessageMapper.criarMensagemInternaSucessoCadastro(usuarioDaMensagem);
        Contexto contexto = contextManager.getContexto(usuarioDaMensagem);
        contexto.put("notificarCadastroSucesso", true);
        mensagemInterna.setContexto(contexto);
        botService.manipulaEvento(mensagemInterna);
    }
}
