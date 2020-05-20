package com.ages.incluitech.backend.chatbotservice.business.service;

import com.ages.incluitech.backend.chatbotservice.business.service.conjutos.TestClienteMockedConjutoRegras;
import com.ages.incluitech.backend.chatbotservice.business.service.conjutos.TestDefaultConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.ComponentBotMessageType;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.service.BotEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BotEngineTest {

    private BotEngine botEngine;

    @Before
    public void setup() {
        this.botEngine = new BotEngine(Arrays.asList(new TestClienteMockedConjutoRegras(),
                new TestDefaultConjuntoRegras()));
    }

    @Test
    public void shouldSelectClienteConjutoRegrasWhenUserTypeIsCliente() {
        //given:
        MensagemInterna mensagemInterna = new MensagemInterna(
                new UsuarioDaMensagem("123", TipoUsuario.CLIENTE),
                TipoMensagem.BOTAO,
                "conteudo",
                new Context()
        );
        //when
        Optional<ConjuntoRegra> conjuntoRegra = botEngine.selecionaConjunto(mensagemInterna);
        Optional<BotMessage> message = conjuntoRegra.flatMap(regra -> regra.processaRegras(mensagemInterna));
        //then
        Assert.assertTrue(conjuntoRegra.isPresent());
        Assert.assertTrue(message.isPresent());
        Assert.assertTrue(conjuntoRegra.get() instanceof TestClienteMockedConjutoRegras);
        Assert.assertEquals(1, message.get().getMessages().size());
        Assert.assertEquals(ComponentBotMessageType.BOTAO, message.get().getMessages().get(0).getType());
    }

    @Test
    public void shouldAlterContext() {
        //given:
        Context contexto = new Context();
        contexto.put("valor", "qualquerUm");
        MensagemInterna mensagemInterna = new MensagemInterna(
                new UsuarioDaMensagem("123", TipoUsuario.CLIENTE),
                TipoMensagem.BOTAO,
                "conteudo",
                contexto
        );
        //when
        Optional<ConjuntoRegra> conjuntoRegra = botEngine.selecionaConjunto(mensagemInterna);
        Optional<BotMessage> message = conjuntoRegra.flatMap(regra -> regra.processaRegras(mensagemInterna));
        //then
        Assert.assertTrue(conjuntoRegra.isPresent());
        Assert.assertTrue(message.isPresent());
        Assert.assertTrue(conjuntoRegra.get() instanceof TestClienteMockedConjutoRegras);
        Assert.assertEquals(1, message.get().getMessages().size());
        Assert.assertEquals(ComponentBotMessageType.TEXTO, message.get().getMessages().get(0).getType());
        Assert.assertTrue(message.get().getContexto().containsKey("valor"));
        Assert.assertTrue(message.get().getContexto().containsKey("tags"));
        Assert.assertEquals(Arrays.asList("tag1", "tag2"), message.get().getContexto().get("tags"));
    }

    @Test
    public void shouldFallIntoDefaultConjuntoRegras() {
        //given:
        MensagemInterna mensagemInterna = new MensagemInterna(
                new UsuarioDaMensagem("123", TipoUsuario.SOLUCIONADOR),
                TipoMensagem.BOTAO,
                "conteudo",
                new Context()
        );
        //when
        Optional<ConjuntoRegra> conjuntoRegra = botEngine.selecionaConjunto(mensagemInterna);
        Optional<BotMessage> message = conjuntoRegra.flatMap(regra -> regra.processaRegras(mensagemInterna));
        //then
        Assert.assertTrue(conjuntoRegra.isPresent());
        Assert.assertFalse(message.isPresent());
        Assert.assertTrue(conjuntoRegra.get() instanceof TestDefaultConjuntoRegras);
    }
}
