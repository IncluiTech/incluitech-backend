package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.usuario.ClienteStub;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository repository;


    @Test
    public void findAllClientesShouldReturnClient() {
        // arrange
        Cliente cliente = ClienteStub.getModelStub();
        when(repository.findAll()).thenReturn(Lists.newArrayList(cliente));

        // act
        List<ClienteResponse> clients = clienteService.findAllClientes();

        // assert
        Optional<ClienteResponse> response = clients.stream().filter(client -> client.getId().equals(cliente.getId())).findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), cliente.getEmail());
        assertEquals(response.get().getEspecialidades(), cliente.getEspecialidades());
        assertEquals(response.get().getNome(), cliente.getNome());
        assertEquals(cliente.getSobrenome(), response.get().getSobrenome());
        assertEquals(response.get().getStatusCadastro(), cliente.getStatusCadastro());
        verify(repository, times(1)).findAll();

    }
}