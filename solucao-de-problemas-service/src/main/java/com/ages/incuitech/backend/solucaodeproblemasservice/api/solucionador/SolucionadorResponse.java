package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorResponse {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String lattes;
    private List<Tag> tags;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
}
