package com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Usuario.UsuarioRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.usuario.UsuarioStub;
import org.assertj.core.util.Lists;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Usuario.UsuarioRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Test
    public void findAllClientes() {
        // arrange
        Usuario usuario = UsuarioStub.usuario();
        when(usuarioRepository.findAll()).thenReturn(Lists.newArrayList(usuario));

        // act
        List<ClienteResponse> clients = clienteService.findAllClientes();

        // assert
        Optional<ClienteResponse> response = clients.stream().filter(client -> client.getId().equals(usuario.getId())).findFirst();
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), usuario.getEmail());
        assertEquals(response.get().getEspecialidades(), usuario.getEspecialidades());
        assertEquals(response.get().getNome(), usuario.getNome());
        assertEquals(usuario.getSobrenome(), response.get().getSobrenome());
        assertEquals(response.get().getStatusCadastro(), usuario.getStatusCadastro());
        verify(usuarioRepository, times(1)).findAll();

    }
}