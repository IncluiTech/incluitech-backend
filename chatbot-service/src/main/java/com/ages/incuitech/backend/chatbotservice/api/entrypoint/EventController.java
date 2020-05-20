package com.ages.incuitech.backend.chatbotservice.api.entrypoint;


import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.business.service.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.inject.*;

@Controller
@RequestMapping("/event")
public class EventController {
    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Inject
    private BotService botService;

    @Inject
    private UserService userService;

    @PutMapping("/{facebookId}")
    public ResponseEntity<String> notificarUsuarioCadastrado(@PathVariable("facebookId") String facebookId) {
        log.info("Facebook id received: " + facebookId);
        UsuarioDaMensagem usuario = userService.getUsuario(facebookId);
        botService.manipulaEvento(MessageMapper.criarMensagemInternaComUsuario(usuario));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
