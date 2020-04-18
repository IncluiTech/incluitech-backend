package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@ToString
public abstract class Button {
    private String type;
    private String title;

    public Button(String type, String title) {
        this.type = type;
        this.title = title;
    }
}
