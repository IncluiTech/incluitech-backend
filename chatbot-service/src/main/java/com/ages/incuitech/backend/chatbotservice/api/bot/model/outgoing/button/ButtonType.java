package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonType {
  WEB_URL("web_url"),
  POSTBACK("postback");

  private String type;
}
