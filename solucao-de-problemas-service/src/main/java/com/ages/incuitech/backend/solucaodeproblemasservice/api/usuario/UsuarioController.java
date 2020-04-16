package com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.exception.ResourceNotFoundException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("v1/user")
public class UsuarioController {

    private UsuarioService usuarioService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponse> inserirUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        log.info("Salvando usuário: {}", usuarioRequest);
        Usuario usuarioSalvo = usuarioService.salvar(usuarioRequest);
        log.info("Usuário salvo: {}", usuarioSalvo);
        return ResponseEntity.ok(UsuarioMapper.mapToResponse(usuarioSalvo));
    }

    @RequestMapping("/{id}")
    public ResponseEntity buscarUsuario(@PathVariable Long id) {
        try {
            log.info("Iniciando busca por usuário com id {}", id);
            Usuario usuarioSalvo = usuarioService.buscar(id);
            log.info("Usuário com id {} encontrado", id);
            return ResponseEntity.ok(UsuarioMapper.mapToResponse(usuarioSalvo));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
