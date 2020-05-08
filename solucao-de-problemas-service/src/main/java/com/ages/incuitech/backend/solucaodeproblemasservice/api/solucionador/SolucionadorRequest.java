package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorRequest {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String lattes;
    private List<String> tags;
}
