package com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.domain.Especialidade;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.domain.StatusCadastro;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
public class Usuario {

    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private List<Especialidade> especialidades;
    private StatusCadastro statusCadastro;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
