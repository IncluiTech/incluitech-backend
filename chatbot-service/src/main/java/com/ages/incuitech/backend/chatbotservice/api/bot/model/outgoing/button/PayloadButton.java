package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Builder @ToString
public class PayloadButton extends Button{
    private String payload;

    public PayloadButton() {
        super(ButtonType.POSTBACK.getValue());
    }
}
