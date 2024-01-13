package com.example.dentalcare.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dentalcare.models.Produto;

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


    public static ArrayList<Produto> parserJsonProdutos(JSONArray response) {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject produtoJson = (JSONObject) response.get(i);
                int id = produtoJson.getInt("id");
                String nome = produtoJson.getString("nome");
                String descricao = produtoJson.getString("descricao");
                String precounitarioString = produtoJson.getString("precounitario");
                double precounitario= Double.parseDouble(precounitarioString);

                Produto produto = new Produto(id,nome,descricao,precounitario);
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
            String nome = produtoJson.getString("nome");
            String descricao = produtoJson.getString("descricao");
            String precounitarioString = produtoJson.getString("precounitario");
            double precounitario= Double.parseDouble(precounitarioString);
            produto = new Produto(id,nome,descricao,precounitario);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }









    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
