package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

public class ClienteStub {

    public static Cliente getModelStub() {
        return Cliente.builder()
                .id(1L)
                .email("fulano@gmail.com")
                .nome("Fulano")
                .nome("Fulano")
                .telefone("51999999999")
                .statusCadastro(StatusCadastro.P)
                .build();
    }
}
