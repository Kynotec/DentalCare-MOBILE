package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        if (isValidIPAddress(ipAddress)) {
            // Guarda o IP no SharedPreferences
            saveIpToSharedPreferences(ipAddress);

            // Configurar o IP no Singleton
            SingletonGestorApp.getInstance(getApplicationContext()).setIpAddress(ipAddress);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // Exibigi uma mensagem de erro
            Toast.makeText(this, "Endereço IP inválido", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isValidIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");

        // Verifica se o IP possui 4 partes separadas por pontos
        if (parts.length != 4) {
            return false;
        }

        // Verifica se cada parte é um número válido no intervalo de 0 a 255
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private void saveIpToSharedPreferences(String ipAddress) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("IP_ADDRESS", ipAddress);
        editor.apply();
    }


}