package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.api;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApiMessage {

  private UsuarioDaMensagem user;
  private String conteudo;
}
