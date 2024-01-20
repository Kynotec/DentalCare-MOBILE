package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class ProdutosOffilneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_offilne);

        // Configurar a Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Adicionar um botão de volta à Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Verificar se o contêiner de fragmento está presente no layout
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                // Adicionar o fragmento inicial
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new ListaProdutosFragment())
                        .commit();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Ação ao clicar no botão de volta da Toolbar
        onBackPressed();
        return true;
    }
}

