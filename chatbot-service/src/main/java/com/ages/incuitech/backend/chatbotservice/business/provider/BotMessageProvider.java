package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;

import java.util.Map;

public interface BotMessageProvider<T> {
    BotMessage provide(T argument, Context contexto);
}
