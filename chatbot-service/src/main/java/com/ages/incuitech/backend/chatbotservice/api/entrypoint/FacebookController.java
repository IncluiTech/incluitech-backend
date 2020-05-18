package com.ages.incuitech.backend.chatbotservice.api.entrypoint;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;
import com.ages.incuitech.backend.chatbotservice.business.service.BotService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/webhook")
public class FacebookController {
  private static final Logger log = LoggerFactory.getLogger(FacebookController.class);

  @Value("${facebook.verify.token}")
  private String verifyToken;

  @Inject private BotService botService;

  @GetMapping
  public ResponseEntity<String> verifyWebhook(
      @RequestParam("hub.verify_token") String verifyToken,
      @RequestParam("hub.challenge") String challengeToken) {

    log.info("Receive request to validate token: " + verifyToken);
    if (verifyToken.equals(this.verifyToken)) {
      log.info("Token " + this.verifyToken + " match correctly");
      return ResponseEntity.ok(challengeToken);
    } else return new ResponseEntity<>("Token was not match correctly", HttpStatus.BAD_REQUEST);
  }

  @PostMapping
  public ResponseEntity<String> receiverMessage(@RequestBody UserMessage userMessage) {
    log.info("Message Received: " + userMessage);
    botService.manipulaMensagem(MessageMapper.mensagemDoUsuarioParaMensagemInterna(userMessage));
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
