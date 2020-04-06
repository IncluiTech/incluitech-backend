package com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(UsuarioRequest usuarioRequest) {
        return usuarioRepository.save(UsuarioMapper.mapToModel(usuarioRequest));
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com id "+id+" não encontrado."));
    }
}
