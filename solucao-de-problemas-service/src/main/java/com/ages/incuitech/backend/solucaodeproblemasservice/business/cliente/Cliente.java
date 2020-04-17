package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Especialidade;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import java.util.List;

@Getter
@Builder
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private List<Especialidade> especialidades;
    private StatusCadastro statusCadastro;
}
