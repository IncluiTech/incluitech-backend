package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;

public class SolucionadorStub {
  private SolucionadorStub() {}

  public static Solucionador getModelStub() {
    return Solucionador.builder()
        .id(1L)
        .email("fulano@gmail.com")
        .nome("Fulano")
        .nome("Fulano")
        .telefone("51999999999")
        .statusCadastro(StatusCadastro.P)
        .build();
  }
}
