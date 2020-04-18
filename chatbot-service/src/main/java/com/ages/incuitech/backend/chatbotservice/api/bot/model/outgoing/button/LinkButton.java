package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

@Getter
@ToString
public class LinkButton extends Button {
    private String url;

    public LinkButton(String titulo, String url) {
        super(ButtonType.WEB_URL.getType(), titulo);
        this.url = url;
    }
}
