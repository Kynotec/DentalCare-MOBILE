package com.example.dentalcare.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.Perfil;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {

    public static String parserJsonLogin(String response) { // static para nao ter de fazer new
        String token = null;
        try {
            JSONObject login = new JSONObject(response);

            if (login.getBoolean("success")){
                token = login.getString("token");
            }
            else {
                return token;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String parserJsonUsername(String response) {
        String username = null;
        try {
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success")){
                username = login.getString("username");
            }
            else {
                return username;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return username;
    }

    //Produtos

    public static ArrayList<Produto> parserJsonProdutos(JSONArray response) {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject produtoJson = (JSONObject) response.get(i);
                int id = produtoJson.getInt("id");
                int stock = produtoJson.getInt("stock");
                String nome = produtoJson.getString("nome");
                String descricao = produtoJson.getString("descricao");
                String precounitarioString = produtoJson.getString("precounitario");
                double precounitario= Double.parseDouble(precounitarioString);

                Produto produto = new Produto(id,nome,descricao,precounitario,stock);
                produtos.add(produto);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    public static Produto parserJsonProduto(String response) {
        Produto produto =null;
        try {
            JSONObject produtoJson = new JSONObject(response);
            int id = produtoJson.getInt("id");
            int stock = produtoJson.getInt("stock");
            String nome = produtoJson.getString("nome");
            String descricao = produtoJson.getString("descricao");
            String precounitarioString = produtoJson.getString("precounitario");
            double precounitario= Double.parseDouble(precounitarioString);
            produto = new Produto(id,nome,descricao,precounitario,stock);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }

    //Servicos

    public static ArrayList<Servico> parserJsonServicos(JSONArray response) {
        ArrayList<Servico> servicos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject servicoJson = (JSONObject) response.get(i);
                int id = servicoJson.getInt("id");
                String referencia = servicoJson.getString("referencia");
                String nome = servicoJson.getString("nome");
                String descricao = servicoJson.getString("descricao");
                String ivaspercentagem = servicoJson.getString("ivaspercentagem");
                String precoString = servicoJson.getString("preco");
                double preco= Double.parseDouble(precoString);
                int iva_id = servicoJson.getInt("iva_id");

                Servico servico = new Servico(id,referencia,nome,descricao,ivaspercentagem,preco,iva_id);
                servicos.add(servico);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return servicos;
    }

    public static Servico parserJsonServico(String response) {
        Servico servico =null;
        try {
            JSONObject servicoJson = new JSONObject(response);
            int id = servicoJson.getInt("id");
            String referencia = servicoJson.getString("referencia");
            String nome = servicoJson.getString("nome");
            String descricao = servicoJson.getString("descricao");
            String ivaspercentagem = servicoJson.getString("ivaspercentagem");
            String precoString = servicoJson.getString("preco");
            double preco= Double.parseDouble(precoString);
            int iva_id = servicoJson.getInt("iva_id");
            servico = new Servico(id,referencia,nome,descricao,ivaspercentagem,preco,iva_id);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return servico;
    }

    //Perfil

    public static Perfil parserJsonPerfil(String response) {
        Perfil auxPerfil = null;
        try {
            JSONObject perfil = new JSONObject(response);
            int user_id = perfil.getInt("user_id");
            int telefone = perfil.getInt("telefone");
            int nif = perfil.getInt("nif");
            String nome = perfil.getString("nome");
            String morada = perfil.getString("morada");
            String codigopostal = perfil.getString("codigopostal");
            auxPerfil = new Perfil(user_id, telefone, nif, nome, morada, codigopostal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxPerfil;
    }

    //Diagnosticos



    public static ArrayList<Diagnostico> parserJsonDiagnosticos(JSONArray response) {
        ArrayList<Diagnostico> diagnosticos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject diagnosticoJson = (JSONObject) response.get(i);
                int id = diagnosticoJson.getInt("id");
                String data = diagnosticoJson.getString("data");
                String hora = diagnosticoJson.getString("hora");
                String descricao = diagnosticoJson.getString("descricao");
                int profile_id = diagnosticoJson.getInt("profile_id");
                int consulta_id = diagnosticoJson.getInt("consulta_id");
                String consultadata = diagnosticoJson.getString("consultadata");
                String consultahora = diagnosticoJson.getString("consultahora");

                Diagnostico diagnostico = new Diagnostico(id,profile_id,consulta_id,descricao,data,hora,consultadata,consultahora);
                diagnosticos.add(diagnostico);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return diagnosticos;
    }

    //Faturas



    public static ArrayList<Fatura> parserJsonFaturas(JSONArray response) {
        ArrayList<Fatura> faturas = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject faturaJson = (JSONObject) response.get(i);
                int id = faturaJson.getInt("id");
                String data = faturaJson.getString("data");
                String valortotalString = faturaJson.getString("valortotal");
                double valortotal= Double.parseDouble(valortotalString);
                String ivatotalString = faturaJson.getString("ivatotal");
                double ivatotal= Double.parseDouble(ivatotalString);
                String subtotalString = faturaJson.getString("subtotal");
                double subtotal= Double.parseDouble(subtotalString);
                String estado = faturaJson.getString("estado");
                int profile_id = faturaJson.getInt("profile_id");


                Fatura fatura = new Fatura(id,profile_id,data,valortotal,ivatotal,subtotal,estado);
                faturas.add(fatura);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return faturas;
    }

    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }


}
