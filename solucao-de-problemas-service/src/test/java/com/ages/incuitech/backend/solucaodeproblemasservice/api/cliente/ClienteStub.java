package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

public class ClienteStub {
  private ClienteStub() {}

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
