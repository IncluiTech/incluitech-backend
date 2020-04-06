package com.ages.incuitech.backend.chatbotservice.business.usuario;

import org.springframework.data.annotation.Id;

public class Usuario {

    @Id
    private Long id;
    private String nome;

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
