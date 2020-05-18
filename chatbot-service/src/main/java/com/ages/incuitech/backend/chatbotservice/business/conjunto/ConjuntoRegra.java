package com.ages.incuitech.backend.chatbotservice.business.conjunto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import java.util.List;
import java.util.Optional;

public abstract class ConjuntoRegra {
  private final List<RegraDoBot> regrasDoBot;

  public ConjuntoRegra(List<RegraDoBot> regraDoBot) {
    this.regrasDoBot = regraDoBot;
  }

  public abstract boolean seleciona(TipoUsuario tipoUsuario);

  public Optional<BotMessage> processaRegras(MensagemInterna mensagemInterna) {
    return regrasDoBot
        .stream()
        .filter(regraDoBot -> regraDoBot.verifica(mensagemInterna))
        .findFirst()
        .map(regraDoBot -> regraDoBot.processa(mensagemInterna));
  }
}
