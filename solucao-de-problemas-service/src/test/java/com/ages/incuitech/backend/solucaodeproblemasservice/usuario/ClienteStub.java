package com.ages.incuitech.backend.solucaodeproblemasservice.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

public class ClienteStub {
    private ClienteStub() {
    }

    public static Cliente getModelStub() {
        return Cliente.builder()
                .id(1L)
                .email("fulano@gmail.com")
                .tags(null)
                .nome("Fulano")
                .sobrenome("de Tal")
                .statusCadastro(StatusCadastro.P)
                .build();
    }
}
