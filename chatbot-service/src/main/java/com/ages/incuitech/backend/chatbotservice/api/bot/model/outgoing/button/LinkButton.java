package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@Setter
@ToString
public class LinkButton extends Button {
    private String url;

    public LinkButton() {
        super(ButtonType.WEB_URL.getType());
    }
}
