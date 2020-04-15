package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import org.springframework.data.annotation.Id;

public class Cliente {

    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String senha;
    private String email;
    private String especialidades;

    public Long getId() {
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }
}
