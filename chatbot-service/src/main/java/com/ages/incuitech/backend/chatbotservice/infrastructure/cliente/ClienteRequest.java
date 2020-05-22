package com.ages.incuitech.backend.chatbotservice.infrastructure.cliente;

import com.ages.incuitech.backend.chatbotservice.business.domain.Especialidade;
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
public class ClienteRequest implements Serializable {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String facebookId;
}
