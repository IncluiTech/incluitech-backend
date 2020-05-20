package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.ComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.api.service.FacebookSendService;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.ContextManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.Collections;
import java.util.List;

@Slf4j
public class BotService {
    private final ContextManager contextManager;
    private final UserService userService;
    private final BotEngine botEngine;
    private final FacebookSendService facebookSendService;

    public BotService(ContextManager contextManager, UserService userService, BotEngine botEngine,
                      FacebookSendService facebookSendService) {
        this.contextManager = contextManager;
        this.userService = userService;
        this.botEngine = botEngine;
        this.facebookSendService = facebookSendService;
    }

    @Async
    public void manipulaEvento(MensagemInterna mensagemInterna){
        BotMessage botMessage = processMessage(mensagemInterna);
        facebookSendService.sendMessage(MessageMapper
                .botMessageParaFacebookMessage(botMessage.getMessages(), mensagemInterna));
    }

    @Async
    public void manipulaMensagem(List<MensagemInterna> mensagensInternas) {
        mensagensInternas.forEach(mensagem -> {
            UsuarioDaMensagem usuarioDaMensagem = userService.getUsuario(mensagem.getUsuario().getId());
            mensagem.getUsuario().setTipoUsuario(usuarioDaMensagem.getTipoUsuario());
            Contexto contexto = contextManager.getContexto(mensagem.getUsuario());
            mensagem.setContexto(contexto);
            BotMessage botMessage = processMessage(mensagem);
            contextManager.saveContexto(mensagem.getUsuario(), botMessage.getContexto());
            facebookSendService.sendMessage(MessageMapper
                    .botMessageParaFacebookMessage(botMessage.getMessages(), mensagem)
            );
        });
    }

    private BotMessage processMessage(MensagemInterna mensagem) {
        try {
            log.debug("Processando mensagem -> " + mensagem);
            return botEngine.selecionaConjunto(mensagem)
                    .flatMap(conjuntoRegra -> conjuntoRegra.processaRegras(mensagem))
                    .orElseGet(this::constroiMensagemDefault);
        } catch (Exception e) {
            log.error("Erro ao processar a mensagem " + mensagem, e);
            return constroiMensagemErroInesperado();
        }
    }

    private BotMessage constroiMensagemDefault() {
        List<ComponentBotMessage> componentBotMessages = Collections.singletonList(
                new TextComponentBotMessage("Desculpa, não entendi, pode tentar de outra forma?")
        );
        return new BotMessage(new Contexto(), componentBotMessages);
    }

    private BotMessage constroiMensagemErroInesperado() {
        List<ComponentBotMessage> componentBotMessages = Collections.singletonList(
                new TextComponentBotMessage("Opa, algo de errado aconteceu, por favor, tente mais tarde")
        );
        return new BotMessage(new Contexto(), componentBotMessages);
    }
}
