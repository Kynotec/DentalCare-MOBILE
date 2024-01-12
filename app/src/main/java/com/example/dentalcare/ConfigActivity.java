package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dentalcare.models.SingletonGestorApp;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }

    public void onClickSendIP(View view) {
        EditText editText = findViewById(R.id.etConfiguracaoServidor);
        String ipAddress = editText.getText().toString();

        // Guarda o IP no SharedPreferences
        saveIpToSharedPreferences(ipAddress);

        // Configurar o IP no Singleton
        SingletonGestorApp.getInstance(getApplicationContext()).setIpAddress(ipAddress);

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void saveIpToSharedPreferences(String ipAddress) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IP_ADDRESS", ipAddress);
        editor.apply();
    }


}