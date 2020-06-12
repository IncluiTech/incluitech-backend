package com.ages.incuitech.backend.chatbotservice.infrastructure.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.infrastructure.User.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteRequest implements Serializable, UserRequest {
    private Long id;
    private String nome;
    private String telefone;
    private String funcao;
    private String instituicao;
    private String email;
    private String facebookId;
    private List<String> tags;


    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.CLIENTE;
    }
}
