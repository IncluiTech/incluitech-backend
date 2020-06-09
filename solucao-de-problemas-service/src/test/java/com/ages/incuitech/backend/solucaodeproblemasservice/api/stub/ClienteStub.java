package com.ages.incuitech.backend.solucaodeproblemasservice.api.stub;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import org.assertj.core.util.Lists;

public class ClienteStub {

    public static Cliente getModelStub() {
        return Cliente.builder()
                .id(1L)
                .facebookId("faceId")
                .email("fulano@gmail.com")
                .nome("Fulano")
                .telefone("51999999999")
                .statusCadastro(StatusCadastro.P)
                .build();
    }

    public static ClienteRequest getUpdateRequestStub() {
        return ClienteRequest.builder()
                .email("fulano@gmail.com")
                .facebookId("faceId")
                .tags(Lists.newArrayList("ESCOLA", "TDAH", "FACULDADE"))
                .build();
    }
}
