package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;

import java.util.ArrayList;
import java.util.Arrays;

public class SolucionadorStub {
    private SolucionadorStub() {
    }

    public static Solucionador getModelStub() {
        return Solucionador.builder()
                .id(1L)
                .email("fulano@gmail.com")
                .nome("Fulano")
                .telefone("51999999999")
                .statusCadastro(StatusCadastro.P)
                .build();
    }

    public static SolucionadorRequest getSolucionadorRequest() {
        return SolucionadorRequest.builder()
                .id(1L)
                .email("fulano@gmail.com")
                .nome("Fulano")
                .telefone("51999999999")
                .tags(Arrays.asList("ONG", "ESCOLA"))
                .build();
    }
}
