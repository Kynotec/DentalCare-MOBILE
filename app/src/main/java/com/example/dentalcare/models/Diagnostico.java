package com.example.dentalcare.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Diagnostico {

    private int id, profile_id, consulta_id;

    private String descricao;

    private LocalDate data;

    private LocalTime hora;

    public Diagnostico(int id,int profile_id, int consulta_id, String descricao, LocalDate data, LocalTime hora) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.profile_id = profile_id;
        this.consulta_id = consulta_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getProfile_id() {return profile_id;}

    public void setProfile_id(int profile_id) {this.profile_id = profile_id;}

    public int getConsulta_id() {return consulta_id;}

    public void setConsulta_id(int consulta_id) {this.consulta_id = consulta_id;}
}
