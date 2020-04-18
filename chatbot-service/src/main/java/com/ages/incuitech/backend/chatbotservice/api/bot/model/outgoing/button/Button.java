package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@ToString
public abstract class Button {
    private String type;
    private String title;

    public Button(String type) {
        this.type = type;
    }
}
