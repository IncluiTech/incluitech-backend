package com.ages.incuitech.backend.chatbotservice.api.entrypoint;


import com.ages.incuitech.backend.chatbotservice.business.service.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/v1/notificacao")
public class NotificacaoController {
    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @PutMapping("/{facebookId}")
    public ResponseEntity<String> notificarUsuarioCadastrado(@PathVariable("facebookId") String facebookId) {
        log.info("Facebook id received: " + facebookId);
        this.service.enviarNotificacaoSucessoCadastro(facebookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
