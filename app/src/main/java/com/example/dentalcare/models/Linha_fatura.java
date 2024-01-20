package com.example.dentalcare.models;

import javax.xml.transform.sax.SAXResult;

public class Linha_fatura {

    private int id, fatura_id;

    private Integer produto_id, servico_id;

    private float quantidade,valortotal;

    private double valorunitario,valoriva;

    private String produtonome,serviconome;

    public Linha_fatura(int id,int fatura_id, Integer produto_id, Integer servico_id, float quantidade, float valortotal, double valorunitario, double valoriva, String produtonome, String serviconome ) {
        this.id = id;
        this.quantidade = quantidade;
        this.valortotal = valortotal;
        this.valorunitario = valorunitario;
        this.valoriva = valoriva;
        this.fatura_id = fatura_id;
        this.produto_id = produto_id;
        this.servico_id = servico_id;
        this.produtonome = produtonome;
        this.serviconome = serviconome;
    }

    public String getProdutonome() {
        return produtonome;
    }

    public void setProdutonome(String produtonome) {
        this.produtonome = produtonome;
    }

    public String getServiconome() {
        return serviconome;
    }

    public void setServiconome(String serviconome) {
        this.serviconome = serviconome;
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

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public double getValoriva() {
        return valoriva;
    }

    public void setValoriva(double valoriva) {
        this.valoriva = valoriva;
    }

    public int getFatura_id() {return fatura_id;}

    public void setFatura_id(int fatura_id) {this.fatura_id = fatura_id;}

    public Integer getProduto_id() {return produto_id;}

    public void setProduto_id(Integer produto_id) {this.produto_id = produto_id;}

    public Integer getServico_id() {return servico_id;}

    public void setServico_id(Integer servico_id) {this.servico_id = servico_id;}
}
