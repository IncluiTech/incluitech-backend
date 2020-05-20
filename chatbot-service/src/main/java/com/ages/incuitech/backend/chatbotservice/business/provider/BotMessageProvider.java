package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;

public interface BotMessageProvider<T> {
    BotMessage provide(T argument, Contexto contexto);
}
