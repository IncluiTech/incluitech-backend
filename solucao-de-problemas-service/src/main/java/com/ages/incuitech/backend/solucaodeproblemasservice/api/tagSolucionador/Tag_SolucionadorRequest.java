package com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag_SolucionadorRequest {
    private Long id;
    private Long id_tag;
    private Long id_solucionador;
}
