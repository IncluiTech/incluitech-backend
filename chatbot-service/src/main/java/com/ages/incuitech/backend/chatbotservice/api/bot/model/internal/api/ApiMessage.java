package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.api;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.MessageMapper;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ApiMessage {

    private UsuarioDaMensagem user;
    private String conteudo;

}
