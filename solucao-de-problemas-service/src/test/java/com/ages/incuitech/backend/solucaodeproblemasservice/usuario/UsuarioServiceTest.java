package com.ages.incuitech.backend.solucaodeproblemasservice.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioRepository;
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
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;


    @Test
    public void shouldReturnUser() {
        Usuario usuarioToGet = UsuarioStub.usuario();
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(usuarioToGet));
        Usuario usuario = usuarioService.buscar(1l);

        assertEquals(usuario.getId(), usuarioToGet.getId());
        assertEquals(usuario.getNome(), usuarioToGet.getNome());
        verify(usuarioRepository, times(1)).findById(any());
    }

}
