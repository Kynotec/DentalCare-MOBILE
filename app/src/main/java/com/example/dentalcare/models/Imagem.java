package com.example.dentalcare.models;

public class Imagem {

    private String filename;
    private int produtoId;
    private int servicoId;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }
}
