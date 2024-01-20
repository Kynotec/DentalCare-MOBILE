package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CarrinhoActivity extends AppCompatActivity {

    public static final int ADD=100,DELETE=300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
    }
}