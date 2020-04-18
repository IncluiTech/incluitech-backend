package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AttachmentBotMessagePayload {
    @JsonProperty("template_type")
    private String templateType;
    //@Todo Continuar criação do modelo seguindo o link https://developers.facebook.com/docs/messenger-platform/reference/templates#available_templates
}
