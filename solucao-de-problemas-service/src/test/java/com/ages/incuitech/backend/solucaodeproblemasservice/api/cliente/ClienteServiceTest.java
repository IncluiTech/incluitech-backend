package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
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
public class ClienteServiceTest {

  @InjectMocks private ClienteService ClienteService;

  @Mock private ClienteRepository repository;

  @Test
  public void findAllClienteesShouldReturnCliente() {
    // arrange
    Cliente Cliente = ClienteStub.getModelStub();
    when(repository.findAll()).thenReturn(Lists.newArrayList(Cliente));

    // act
    List<ClienteResponse> Clientes = ClienteService.findAllClientes();

    // assert
    Optional<ClienteResponse> response =
        Clientes.stream()
            .filter(ClienteResponse -> ClienteResponse.getId().equals(Cliente.getId()))
            .findFirst();
    assertTrue(response.isPresent());
    assertEquals(response.get().getEmail(), Cliente.getEmail());
    assertEquals(response.get().getNome(), Cliente.getNome());
    assertEquals(response.get().getTelefone(), Cliente.getTelefone());
    assertEquals(response.get().getStatusCadastro(), Cliente.getStatusCadastro());
    verify(repository).findAll();
  }
}
