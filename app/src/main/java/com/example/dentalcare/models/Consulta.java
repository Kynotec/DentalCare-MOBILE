package com.example.dentalcare.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private int id, profile_id, servico_id;

    private String descricao, estado, data, hora, nomeservico;


    public Consulta(int id, int servico_id, String descricao, String data, String hora, String estado, String nomeservico) {
        this.id = id ;
        this.servico_id = servico_id ;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.estado = estado;
        this.nomeservico = nomeservico;
    }


    public String getNomeservico() {
        return nomeservico;
    }

    public void setNomeservico(String nomeservico) {
        this.nomeservico = nomeservico;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {return hora;}

    public void setHora(String hora) {this.hora = hora;}

    public int getProfile_id() {return profile_id;}

    public void setProfile_id(int profile_id) {this.profile_id = profile_id;}

    public int getServico_id() {return servico_id;}

    public void setServico_id(int servico_id) {this.servico_id = servico_id;}
}
