package com.example.dentalcare.models;

import android.content.Context;
import android.util.Base64;
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
import com.example.dentalcare.listeners.LoginListener;
import com.example.dentalcare.utils.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingletonGestorApp {

    private String ipAddress;  // Variável para armazenar o endereço IP
    private static SingletonGestorApp instance = null;
    private static RequestQueue volleyQueue = null;
    private LoginListener loginListener;
    private BDHelper BD;

    private SingletonGestorApp(Context context) {
        BD = new BDHelper(context);
    }

    public static synchronized SingletonGestorApp getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorApp(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    //Adiciona o IP no Singleton
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;

    }
    //Buscar o IP
    public String getIpAddress() {
        return ipAddress;
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
}
