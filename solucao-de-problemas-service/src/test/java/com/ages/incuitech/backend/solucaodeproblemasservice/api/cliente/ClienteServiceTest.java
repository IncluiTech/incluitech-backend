package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.ClienteStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.stub.UserTagStub;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository repository;

    @Mock
    private TagClienteRepository tagClienteRepository;

    @Mock
    private TagService tagService;


    @Test
    public void findAllClientesShouldReturnCliente() {
        // arrange
        Cliente cliente = ClienteStub.getModelStub();
        when(repository.findAll()).thenReturn(Lists.newArrayList(cliente));
        when(tagClienteRepository.findAllLinkedTags()).thenReturn(UserTagStub.getUserTagStub());

        // act
        List<ClienteResponse> clientes = clienteService.findAllClientes();

        // assert
        Optional<ClienteResponse> response = clientes
                .stream()
                .filter(clienteResponse -> clienteResponse.getId().equals(cliente.getId()))
                .findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), cliente.getEmail());
        assertEquals(response.get().getNome(), cliente.getNome());
        assertEquals(response.get().getTelefone(), cliente.getTelefone());
        assertEquals(response.get().getStatusCadastro(), cliente.getStatusCadastro());
        assertFalse(response.get().getTags().isEmpty());
        assertEquals(response.get().getTags().size(), 3);
        assertEquals(response.get().getTags().get(0), "TDAH");
        assertEquals(response.get().getTags().get(1), "CRINACAS");
        assertEquals(response.get().getTags().get(2), "ESCOLA");
        verify(repository).findAll();
    }

    @Test
    public void deveClienteClienteComSuasTags() {
        // arrange
        Cliente entity = ClienteStub.getModelStub();
        ClienteRequest request = ClienteStub.getUpdateRequestStub();
        when(repository.findByIdFacebook("faceId")).thenReturn(entity);
        when(repository.save(any())).thenReturn(entity);
        when(tagService.salvar("ESCOLA")).thenReturn(Tag.builder().id(1L).nome("ESCOLA").build());
        when(tagService.salvar("TDAH")).thenReturn(Tag.builder().id(1L).nome("TDAH").build());
        when(tagService.salvar("FACULDADE")).thenReturn(Tag.builder().id(1L).nome("FACULDADE").build());

        // act
        ClienteResponse response = clienteService.update(request);

        // assert
        assertEquals(response.getEmail(), entity.getEmail());
        assertEquals(response.getNome(), entity.getNome());
        assertEquals(response.getTelefone(), entity.getTelefone());
        assertEquals(response.getStatusCadastro(), entity.getStatusCadastro());
        assertFalse(response.getTags().isEmpty());
        assertEquals(response.getTags().size(), 3);
        assertEquals(response.getTags().get(0), "ESCOLA");
        assertEquals(response.getTags().get(1), "TDAH");
        assertEquals(response.getTags().get(2), "FACULDADE");
        verify(repository).findAll();
    }

}