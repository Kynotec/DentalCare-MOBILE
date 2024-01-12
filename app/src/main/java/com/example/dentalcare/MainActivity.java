package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dentalcare.models.SingletonGestorApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenha o endereço IP do SingletonGestorApp
        String ipAddress = SingletonGestorApp.getInstance(getApplicationContext()).getIpAddress();

        // Encontre a TextView usando o ID
        TextView tvIP = findViewById(R.id.tvIP);

        // Configure o texto da TextView com o endereço IP
        tvIP.setText(ipAddress);

    }

    public void onClickLogin(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}