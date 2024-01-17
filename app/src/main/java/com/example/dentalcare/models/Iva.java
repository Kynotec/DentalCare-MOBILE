package com.example.dentalcare.models;

public class Iva {

    private int id,emvigor;

    private String percentagem,descricao;


    public Iva(int id, int emvigor, String percentagem, String descricao) {
        this.id = id;
        this.emvigor = emvigor;
        this.percentagem = percentagem;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmvigor() {
        return emvigor;
    }

    public void setEmvigor(int emvigor) {
        this.emvigor = emvigor;
    }

    public String getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(String percentagem) {
        this.percentagem = percentagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
