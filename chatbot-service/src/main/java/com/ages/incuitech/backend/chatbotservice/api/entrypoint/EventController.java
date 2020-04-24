package com.ages.incuitech.backend.chatbotservice.api.entrypoint;


import com.ages.incuitech.backend.chatbotservice.api.bot.model.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.api.ApiMessage;
import com.ages.incuitech.backend.chatbotservice.business.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/Event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Inject
    private BotService botService;
    public ResponseEntity<String> receiverEvent(@RequestBody ApiMessage eventMessage) {
        log.info("Event Received: " + eventMessage);
        botService.manipulaEvento(MessageMapper.converteApiMessageParaInterna(eventMessage));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
