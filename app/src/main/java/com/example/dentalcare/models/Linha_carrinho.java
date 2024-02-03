package com.example.dentalcare.models;

public class Linha_carrinho {

    String imagem, nome;
    private int id;

    private double quantidade, valortotal;


    public Linha_carrinho(int id, double quantidade, double valortotal, String imagem,String nome) {
        this.id = id;
        this.quantidade = quantidade;
        this.valortotal = valortotal;
        this.imagem = imagem;
        this.nome = nome;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public String getImagem() {return imagem;}

    public void setImagem(String imagem) {this.imagem = imagem;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
