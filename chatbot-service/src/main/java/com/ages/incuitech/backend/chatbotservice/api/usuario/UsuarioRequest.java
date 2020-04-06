package com.ages.incuitech.backend.chatbotservice.api.usuario;

public class UsuarioRequest {

    private String nome;

    public UsuarioRequest() { }
    public UsuarioRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
