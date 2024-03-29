package com.example.dentalcare.models;

import java.time.LocalDate;

public class Fatura {

    private int id, profile_id;

    private String data;

    private double valortotal,ivatotal,subtotal;

    private String estado;

    public Fatura(int id,int profile_id, String data, double valortotal, double ivatotal, double subtotal, String estado) {
        this.id = id;
        this.profile_id = profile_id;
        this.data = data;
        this.valortotal = valortotal;
        this.ivatotal = ivatotal;
        this.subtotal = subtotal;
        this.estado = estado;
    }


    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public double getIvatotal() {
        return ivatotal;
    }

    public void setIvatotal(double ivatotal) {
        this.ivatotal = ivatotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getProfile_id() {return profile_id;}

    public void setProfile_id(int profile_id) {this.profile_id = profile_id;}
}
