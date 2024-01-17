package com.example.dentalcare.models;

public class Empresa {

    private int id,telefone,nif,codigopostal;

    private float capitalsocial;

    private String designacaosocial,email,morada,localidade;


    public Empresa(int id, int telefone, int nif, int codigopostal, float capitalsocial, String designacaosocial, String email, String morada, String localidade) {
        this.id = id;
        this.telefone = telefone;
        this.nif = nif;
        this.codigopostal = codigopostal;
        this.capitalsocial = capitalsocial;
        this.designacaosocial = designacaosocial;
        this.email = email;
        this.morada = morada;
        this.localidade = localidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    public float getCapitalsocial() {
        return capitalsocial;
    }

    public void setCapitalsocial(float capitalsocial) {
        this.capitalsocial = capitalsocial;
    }

    public String getDesignacaosocial() {
        return designacaosocial;
    }

    public void setDesignacaosocial(String designacaosocial) {
        this.designacaosocial = designacaosocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
