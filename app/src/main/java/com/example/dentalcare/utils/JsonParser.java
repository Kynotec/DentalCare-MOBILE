package com.example.dentalcare.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dentalcare.models.Carrinho;
import com.example.dentalcare.models.Consulta;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.Linha_carrinho;
import com.example.dentalcare.models.Linha_fatura;
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
                String imagem ="http://172.22.21.201/DentalCare-WEB/DentalCare/public/images/products/" + produtoJson.getString("imagem");
                Produto produto = new Produto(id,nome,descricao,precounitario,stock,imagem);
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
            String imagem = produtoJson.getString("imagem");
            produto = new Produto(id,nome,descricao,precounitario,stock,imagem);
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

                Servico servico = new Servico(id,referencia,nome,descricao,ivaspercentagem,preco);
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

            servico = new Servico(id,referencia,nome,descricao,ivaspercentagem,preco);
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


    //Linha Faturas



    public static ArrayList<Linha_fatura> parserJsonLinhaFaturas(JSONArray response) {
        ArrayList<Linha_fatura> linha_faturas = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject linhafaturaJson = (JSONObject) response.get(i);

                int id = linhafaturaJson.getInt("id");
                int fatura_id = linhafaturaJson.getInt("fatura_id");

                // Verificando se os campos produto_id e servico_id existem no JSON
                Integer produto_id = linhafaturaJson.isNull("produto_id") ? null : linhafaturaJson.getInt("produto_id");
                Integer servico_id = linhafaturaJson.isNull("servico_id") ? null : linhafaturaJson.getInt("servico_id");

                String quantidadeString = linhafaturaJson.getString("quantidade");
                float quantidade = Float.parseFloat(quantidadeString);

                String valortotalString = linhafaturaJson.getString("valortotal");
                float valortotal = Float.parseFloat(valortotalString);

                String valorunitarioString = linhafaturaJson.getString("valorunitario");
                double valorunitario = Double.parseDouble(valorunitarioString);

                String valorivaString = linhafaturaJson.getString("valoriva");
                double valoriva = Double.parseDouble(valorivaString);
                String produtonome = linhafaturaJson.getString("produtonome");
                String serviconome = linhafaturaJson.getString("serviconome");

                Linha_fatura linha_fatura = new Linha_fatura(id, fatura_id, produto_id, servico_id, quantidade, valortotal, valorunitario, valoriva,produtonome,serviconome);
                linha_faturas.add(linha_fatura);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return linha_faturas;
    }
    public static ArrayList<Linha_carrinho> parserJsonCarrinho(JSONArray response)
    {
        ArrayList<Linha_carrinho> linhaCarrinhos = new ArrayList<>();

        try {
            for (int i=0; i< response.length();i++) {
                JSONObject jsonObject = (JSONObject) response.getJSONObject(i);
                JSONObject produto = jsonObject.getJSONObject("produto");
                int id=produto.getInt("id");
                String nome = produto.getString("nome");
                String descricao = produto.getString("descricao");
                double precounitario = produto.getDouble("precounitario");
                int stock = produto.getInt("stock");
                String data = jsonObject.getString("data");
                double valortotal = jsonObject.getDouble("valortotal");
                Produto produtoAux = new Produto(id,nome,descricao,precounitario,stock,null);
             //   Linha_carrinho linhaCarrinho = new Carrinho(produtoAux,data,valortotal);
                //linhaCarrinhos.add(linhaCarrinho);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return linhaCarrinhos;
    }

    //Consultas
    public static ArrayList<Consulta> parserJsonMarcacoes(JSONArray response) {
        ArrayList<Consulta> marcacoes = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject marcacaoJson = (JSONObject) response.get(i);
                int id = marcacaoJson.getInt("id");
                int profile_id = marcacaoJson.getInt("profile_id");
                int servico_id = marcacaoJson.getInt("servico_id");
                String descricao = marcacaoJson.getString("descricao");
                String estado = marcacaoJson.getString("estado");
                String data = marcacaoJson.getString("data");
                String hora = marcacaoJson.getString("hora");
                String nomeservico = marcacaoJson.getString("nomeservico");

                Consulta marcacao = new Consulta(id,profile_id,servico_id,descricao,estado,data,hora,nomeservico);
                marcacoes.add(marcacao);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return marcacoes;
    }

    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }



}
