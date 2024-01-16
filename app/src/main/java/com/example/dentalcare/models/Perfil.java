package com.example.dentalcare.models;

public class Perfil {
        private int user_id, telefone, nif;
        private String nome, morada, codigopostal;

        public Perfil(int user_id, int telefone, int nif, String nome, String morada, String codigopostal) {
            this.user_id = user_id;
            this.telefone = telefone;
            this.nif = nif;
            this.nome = nome;
            this.morada = morada;
            this.codigopostal = codigopostal;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMorada() {
            return morada;
        }

        public void setMorada(String morada) {
            this.morada = morada;
        }

        public String getCodigopostal() {
            return codigopostal;
        }

        public void setCodigopostal(String codigopostal) {
            this.codigopostal = codigopostal;
        }
}
