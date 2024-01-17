package com.example.dentalcare.models;

public class Linha_carrinho {

    private int id, carrinho_id, produto_id;

    private float quantidade, valortotal;

    private double valoriva;

    public Linha_carrinho(int id,int carrinho_id, int produto_id, float quantidade, float valortotal, double valoriva) {
        this.id = id;
        this.quantidade = quantidade;
        this.valortotal = valortotal;
        this.valoriva = valoriva;
        this.carrinho_id = carrinho_id;
        this.produto_id = produto_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValortotal() {
        return valortotal;
    }

    public void setValortotal(float valortotal) {
        this.valortotal = valortotal;
    }

    public double getValoriva() {
        return valoriva;
    }

    public void setValoriva(double valoriva) {
        this.valoriva = valoriva;
    }

    public int getCarrinho_id() {return carrinho_id;}

    public void setCarrinho_id(int carrinho_id) {this.carrinho_id = carrinho_id;}

    public int getProduto_id() {return produto_id;}

    public void setProduto_id(int produto_id) {this.produto_id = produto_id;}
}
