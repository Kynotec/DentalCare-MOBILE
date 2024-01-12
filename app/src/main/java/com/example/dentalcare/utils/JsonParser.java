package com.example.dentalcare.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static Boolean isConnectionInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
