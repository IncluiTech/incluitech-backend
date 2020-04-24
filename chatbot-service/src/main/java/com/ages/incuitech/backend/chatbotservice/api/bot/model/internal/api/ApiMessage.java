package com.ages.incuitech.backend.chatbotservice.api.entrypoint;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
public class ApiMessage {

    private UsuarioDaMensagem user;
    private String conteudo;

}
