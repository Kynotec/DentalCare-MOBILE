package com.example.dentalcare.models;

public class Produto {

    private int id;

    private String nome,descricao;

    private double precounitario;


    public Produto(int id, String nome, String descricao, double precounitario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precounitario = precounitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecounitario() {
        return precounitario;
    }

    public void setPrecounitario(double precounitario) {
        this.precounitario = precounitario;
    }
}