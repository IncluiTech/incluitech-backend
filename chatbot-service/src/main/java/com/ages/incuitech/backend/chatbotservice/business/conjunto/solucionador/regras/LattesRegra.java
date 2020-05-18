package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorMapper;

public class LattesRegra implements RegraDoBot {

  private final SolucaoDeProblemasClient client;

  public LattesRegra(SolucaoDeProblemasClient client) {
    this.client = client;
  }

  @Override
  public boolean verifica(MensagemInterna message) {
    return message.getContexto().get("aguardandoLattes").equals(true)
        && this.validarTipoMensagem(message);
  }

  @Override
  public BotMessage processa(MensagemInterna message) {
    String conteudo = message.getConteudo();
    if (conteudo.equals(NAO.name())) {
      return new BotMessage(message.getContexto())
          .withMessages(
              new TextComponentBotMessage(
                  "Sem problemas! Agora irei enviar seu perfil para os especialistas "
                      + "da Incluitec, para que ele seja aprovado.Vou te avisar assim que estiver tudo certo."));
    }

    message.getContexto().put("lattes", conteudo);
    client.updateSolucionador(
        SolucionadorMapper.criarRequestAPartirDeContexto(
            message.getContexto(), message.getUsuario().getId()));
    return new BotMessage(message.getContexto())
        .withMessages(
            new TextComponentBotMessage(
                "Legal! Agora irei enviar seu perfil para os especialistas da Incluitec, "
                    + "para que ele seja aprovado.Vou te avisar assim que estiver tudo certo."));
  }

  private boolean validarTipoMensagem(MensagemInterna message) {
    return message.getTipo() == TipoMensagem.TEXTO || message.getTipo() == TipoMensagem.BOTAO;
  }
}
