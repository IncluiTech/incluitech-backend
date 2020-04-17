package com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Model for the webhook events.
 *
 * @author ramswaroop
 * @version 26/09/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Messaging {
    private User sender;
    private User recipient;
    private Long timestamp;
    private Message message;
    private Postback postback;
}
