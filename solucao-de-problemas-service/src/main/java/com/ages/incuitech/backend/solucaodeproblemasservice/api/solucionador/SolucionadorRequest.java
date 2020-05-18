package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import lombok.*;

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
    private String facebookId;
}
