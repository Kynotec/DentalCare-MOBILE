package com.example.dentalcare.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentalcare.LoginActivity;
import com.example.dentalcare.MenuMainActivity;
import com.example.dentalcare.R;
import com.example.dentalcare.listeners.DetalhesProdutoListener;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.listeners.LoginListener;
import com.example.dentalcare.listeners.PerfilListener;
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.listeners.ServicosListener;
import com.example.dentalcare.utils.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorApp {

    private String ipAddress;  // Variável para armazenar o endereço IP

    private ArrayList<Produto> produtos;
    private ArrayList<Servico> servicos;

    private Perfil perfils;

    private static SingletonGestorApp instance = null;
    private static RequestQueue volleyQueue = null;
    private LoginListener loginListener;
    private PerfilListener perfilListener;
    private ProdutosListener produtosListener;
    private ServicosListener servicosListener;
    private DetalhesServicoListener detalhesServicoListener;
    private DetalhesProdutoListener detalhesProdutoListener;
    private BDHelper BD;



    public static synchronized SingletonGestorApp getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorApp(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    private SingletonGestorApp(Context context) {
        // BD = new BDHelper(context);
        produtos = new ArrayList<>();
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void setProdutosListener(ProdutosListener produtosListener){
        this.produtosListener = produtosListener;
    }
    public void setServicosListener(ServicosListener servicosListener){
        this.servicosListener = servicosListener;
    }

    public void setPerfilListener(PerfilListener perfilListener) {
        this.perfilListener = perfilListener;
    }

    public void setDetalhesServicoListener(DetalhesServicoListener detalhesServicoListener) {
        this.detalhesServicoListener = detalhesServicoListener;
    }

    public void setDetalhesProdutoListener(DetalhesProdutoListener detalhesProdutoListener) {
        this.detalhesProdutoListener = detalhesProdutoListener;
    }

    //Adiciona o IP no Singleton
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;

    }
    //Buscar o IP
    public String getIpAddress() {
        return ipAddress;
    }

    public Servico getServico(int id) {
        for (Servico s : servicos) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public Produto getProduto(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }
    public void loginAPI(final String username, final String password, final Context context) {
        // Obter o endereço IP armazenado nas SharedPreferences
        String ipAddress = SingletonGestorApp.getInstance(context).getIpAddress();

        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação à internet", Toast.LENGTH_LONG).show();
        } else {
            // Substituir o valor fixo de APILogin pelo endereço IP
            final String APILoginWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/auth/login";

            StringRequest req = new StringRequest(Request.Method.GET, APILoginWithIP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String token = JsonParser.parserJsonLogin(response);
                    String username = JsonParser.parserJsonUsername(response);

                    if (loginListener != null)
                        loginListener.onValidateLogin(token, username, context);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Username ou password incorreto", Toast.LENGTH_LONG).show();
                    VolleyLog.d("LOGIN: Error" + error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    String credentials = username + ":" + password;
                    String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Basic " + base64EncodedCredentials);
                    return headers;
                }
            };
            volleyQueue.add(req);
        }
    }

    public void getPerfilAPI(final Context context, String token) {
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {
            if (token != null && !token.trim().isEmpty()) {
                // Make the API request
            } else {
                // Handle the case when the token is null or empty
                Log.e("API_ERROR", "Invalid access token");
                // Add appropriate handling or notify the user
            }


            final String APIPerfilWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/user";
            StringRequest req = new StringRequest(Request.Method.GET, APIPerfilWithIP + "/get-perfil?=" + token,  new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    perfils = JsonParser.parserJsonPerfil(response);
                    if(perfilListener !=null)
                        perfilListener.onShowPerfil(perfils);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("TOKEN_DEBUG", "Access token: " + token);
                    Log.d("API_REQUEST_DEBUG", "API URL: " + APIPerfilWithIP + "/get-perfil?=" + token);

                    Toast.makeText(context, "Error during API request", Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }

    public void editPerfilAPI(final Perfil perfil, final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        } else {
            final String APIPerfilWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/user";
            StringRequest req = new StringRequest(Request.Method.PUT, APIPerfilWithIP + "atualizar-perfil?=" + token,  new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (perfilListener != null)
                        perfilListener.onShowPerfil(perfil);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("nome", perfil.getNome());
                    params.put("telemovel", perfil.getTelefone() + "");
                    params.put("nif", perfil.getNif() + "");
                    params.put("codpostal", perfil.getCodigopostal() + "");
                    params.put("morada", perfil.getMorada());
                    return params;
                }
            };
            volleyQueue.add(req);
        }

    }


    public void getAllProdutosAPI(final Context context) {
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {

            final String APIProdutoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/produto";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIProdutoWithIP, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    produtos = JsonParser.parserJsonProdutos(response);
                    if (produtosListener != null)
                        produtosListener.onRefreshListaProdutos(produtos);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(jsonArrayRequest);
        }
    }

    public void getAllServicosAPI(final Context context) {
        if(!JsonParser.isConnectionInternet(context)){
            Toast.makeText(context, context.getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
        }else {

            final String APIServicoWithIP = "http://" + ipAddress + "/DentalCare-SIS-PSI/backend/web/api/servico";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, APIServicoWithIP, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    servicos = JsonParser.parserJsonServicos(response);
                    if (servicosListener != null)
                        servicosListener.onRefreshListaServicos(servicos);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(jsonArrayRequest);
        }
    }



/*
    public void getPerfilAPI(final Context context, String token) {
        if (!JsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "Sem ligação á internet", Toast.LENGTH_LONG).show();
        } else {
            StringRequest req = new StringRequest(Request.Method.GET, APIGetPerfil + "?access-token=" + token, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    perfils = PerfilJsonParser.parserJsonPerfil(response);
                    if (perfilListener != null)
                        perfilListener.onMostrarPerfil(perfils);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            volleyQueue.add(req);
        }
    }
*/

}
