package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.ClienteStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.UserTagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagClienteRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService ClienteService;

    @Mock
    private ClienteRepository repository;

    @Mock
    private TagClienteRepository tagClienteRepository;


    @Test
    public void findAllClientesShouldReturnCliente() {
        // arrange
        Cliente Cliente = ClienteStub.getModelStub();
        when(repository.findAll()).thenReturn(Lists.newArrayList(Cliente));
        when(tagClienteRepository.findTagsOfCliente(1L)).thenReturn(UserTagStub.getUserTagStub());

        // act
        List<ClienteResponse> Clientes = ClienteService.findAllClientes();

        // assert
        Optional<ClienteResponse> response = Clientes
                .stream()
                .filter(ClienteResponse -> ClienteResponse.getId().equals(Cliente.getId()))
                .findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), Cliente.getEmail());
        assertEquals(response.get().getNome(), Cliente.getNome());
        assertEquals(response.get().getTelefone(), Cliente.getTelefone());
        assertEquals(response.get().getStatusCadastro(), Cliente.getStatusCadastro());
        assertFalse(response.get().getTags().isEmpty());
        assertEquals(response.get().getTags().size(), 3);
        assertEquals(response.get().getTags().get(0), "TDAH");
        assertEquals(response.get().getTags().get(1), "CRINACAS");
        assertEquals(response.get().getTags().get(2), "ESCOLA");
        verify(repository).findAll();
    }
}