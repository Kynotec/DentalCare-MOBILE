package com.example.dentalcare.models;

public class Servico {

    private int id;

    String imagem;
    private String referencia,nome,descricao, ivaspercentagem;
    private double preco;



    public Servico(int id, String nome, String descricao, String ivaspercentagem,double preco, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ivaspercentagem = ivaspercentagem;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getIvaspercentagem() {
        return ivaspercentagem;
    }

    public void setIvaspercentagem(String ivaspercentagem) {
        this.ivaspercentagem = ivaspercentagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
