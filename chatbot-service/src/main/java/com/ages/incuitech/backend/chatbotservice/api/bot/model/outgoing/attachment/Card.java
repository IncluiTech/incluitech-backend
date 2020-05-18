package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@With
public class Card {
  private String title;

  @JsonProperty("image_url")
  private String imageUrl;

  private String subtitle;
  private List<Button> buttons;
}
