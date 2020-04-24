package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;


/**
 * Classe abstrada mãe dos tipos possiveis de botões fixos.
 * Diferentemente de quickreply esse botões não someme ao serem selecionados
 * @see QuickReplyButton
 * @see PayloadButton
 * @see LinkButton
 */
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
