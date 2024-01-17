package com.example.dentalcare.models;

import java.time.LocalDate;

public class Carrinho {

    private int id, user_id;

    private LocalDate data;
    private double valortotal;

    public Carrinho(int id, int user_id, LocalDate data, double valortotal) {
        this.id = id;
        this.data = data;
        this.valortotal = valortotal;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }
}
