package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Solucionador {

    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String lattes;
    private List<Tag> tags;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;

}
