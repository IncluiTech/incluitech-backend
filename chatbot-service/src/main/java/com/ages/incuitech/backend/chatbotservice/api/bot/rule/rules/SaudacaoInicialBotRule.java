package com.ages.incuitech.backend.chatbotservice.api.bot.rule.rules;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.rule.BotRule;

import java.util.Map;

public class SaudacaoInicialBotRule implements BotRule {
    @Override
    public boolean verify(Object object, Map<String, Object> contexto) {
        return true;
    }

    @Override
    public BotMessage process(Object object, Map<String, Object> contexto) {
        return new BotMessage();
    }
}
