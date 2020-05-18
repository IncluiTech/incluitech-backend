package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SolucionadorServiceTest {

  @InjectMocks private SolucionadorService solucionadorService;

  @Mock private SolucionadorRepository repository;

  @Test
  public void findAllSolucionadoresShouldReturnSolucionador() {
    // arrange
    Solucionador solucionador = SolucionadorStub.getModelStub();
    when(repository.findAll()).thenReturn(Lists.newArrayList(solucionador));

    // act
    List<SolucionadorResponse> solucionadores = solucionadorService.findAllSolucionadores();

    // assert
    Optional<SolucionadorResponse> response =
        solucionadores
            .stream()
            .filter(
                solucionadorResponse -> solucionadorResponse.getId().equals(solucionador.getId()))
            .findFirst();
    assertTrue(response.isPresent());
    assertEquals(response.get().getEmail(), solucionador.getEmail());
    assertEquals(response.get().getNome(), solucionador.getNome());
    assertEquals(response.get().getTelefone(), solucionador.getTelefone());
    assertEquals(response.get().getStatusCadastro(), solucionador.getStatusCadastro());
    verify(repository).findAll();
  }
}
