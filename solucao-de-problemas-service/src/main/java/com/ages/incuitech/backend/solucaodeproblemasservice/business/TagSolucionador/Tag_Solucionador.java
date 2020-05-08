package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Tag_Solucionador {
    @Id
    private Long id;
    private Long id_tag;
    private Long id_solucionador;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
}
