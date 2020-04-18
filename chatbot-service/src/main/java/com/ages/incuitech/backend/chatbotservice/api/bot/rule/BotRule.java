package com.ages.incuitech.backend.chatbotservice.api.bot.rule;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;

import java.util.Map;

public interface BotRule {
    boolean verify(MensagemInterna message, Map<String, Object> contexto);

    BotMessage process(MensagemInterna message, Map<String, Object> contexto);
}
