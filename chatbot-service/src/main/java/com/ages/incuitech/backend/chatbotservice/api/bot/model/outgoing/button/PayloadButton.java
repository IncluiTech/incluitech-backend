package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@Setter
@ToString
public class PayloadButton extends Button {
    private String payload;

    public PayloadButton(String titulo, String conteudo) {
        super(ButtonType.POSTBACK.getType(), titulo);
        this.payload  = conteudo;
    }
}
