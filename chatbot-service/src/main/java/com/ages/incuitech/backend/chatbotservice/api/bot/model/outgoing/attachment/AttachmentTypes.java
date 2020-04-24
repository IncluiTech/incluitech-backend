package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttachmentTypes {
    GENERIC("generic"),BUTTON("button");

    private String valor;
}
