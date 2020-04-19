package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.ConjuntoRegra;

import javax.print.attribute.standard.MediaSize;
import java.util.List;
import java.util.Optional;

public class BotEngine {

    private List<ConjuntoRegra> conjuntoRegras;

    public BotEngine(List<ConjuntoRegra> conjuntoRegras) {
        this.conjuntoRegras = conjuntoRegras;
    }

    public Optional<ConjuntoRegra> selecionaConjunto(MensagemInterna mensagemInterna) {
        return conjuntoRegras.stream()
                .filter(conjunto -> conjunto.seleciona(mensagemInterna.getUsuario().getTipoUsuario()))
                .findFirst();
    }
}
