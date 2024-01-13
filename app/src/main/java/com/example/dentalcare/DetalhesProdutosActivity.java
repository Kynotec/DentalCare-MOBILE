package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.models.Produto;

import java.util.ArrayList;

public class DetalhesProdutosActivity extends AppCompatActivity implements ProdutosListener {


    public static final String ID_PRODUTO = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produtos);
    }

    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaprodutos) {

    }
}