package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@Setter
@ToString
public class PayloadButton extends Button {
    private String payload;

    public PayloadButton() {
        super(ButtonType.POSTBACK.getType());
    }
}
