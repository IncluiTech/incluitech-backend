package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

public class ClienteRequest {

    private String nome;
    private String senha;
    private String sobrenome;
    private String especialidades;
    private String email;
    public ClienteRequest(){
    }

    public ClienteRequest(String email, String senha, String nome, String sobrenome, String especialidades) {
        this.nome = nome;
        this.senha = senha;
        this.sobrenome = sobrenome;
        this.email = email;
        this.especialidades = especialidades;

    }

    public String getNome() {
        return nome;
    }


    public String getSobrenome() {
        return sobrenome;
    }





    public String getEspecialidades() {
        return especialidades;
    }





    public String getEmail() {
        return email;
    }


}
