package com.example.dentalcare.models;

import java.util.List;

public class Produto {


    String imagem;
    private int id;

    private String nome,descricao;

    private double precounitario;


    private int stock;




    public Produto(int id, String nome, String descricao, double precounitario, int stock, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precounitario = precounitario;
        this.stock = stock;
        this.imagem = imagem;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
