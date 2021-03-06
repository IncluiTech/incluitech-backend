package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ButtonsAttachment extends AttachmentBotMessagePayload {
    private String text;
    private List<Button> buttons;

    public ButtonsAttachment(String texto, List<Button> botoes) {
        super(AttachmentTypes.BUTTON.getValor());
        this.text = texto;
        this.buttons = botoes;
    }
}
