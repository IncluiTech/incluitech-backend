package com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Tag;
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
public class TagSolucionadorResponse {
    private Long id;
    private Long id_tag;
    private Long id_solucionador;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
}
