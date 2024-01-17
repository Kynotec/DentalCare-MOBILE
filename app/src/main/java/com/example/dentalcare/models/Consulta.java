package com.example.dentalcare.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    private int id, profile_id, servico_id;

    private String descricao, estado;

    private LocalDate data;

    private LocalTime hora;

    public Consulta(int id, int profile_id, int servico_id, String descricao, String estado, LocalDate data, LocalTime hora) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
        this.data = data;
        this.hora = hora;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {return hora;}

    public void setHora(LocalTime hora) {this.hora = hora;}

    public int getProfile_id() {return profile_id;}

    public void setProfile_id(int profile_id) {this.profile_id = profile_id;}

    public int getServico_id() {return servico_id;}

    public void setServico_id(int servico_id) {this.servico_id = servico_id;}
}
