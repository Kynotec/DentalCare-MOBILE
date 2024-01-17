package com.example.dentalcare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.listeners.ServicosListener;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetalhesServicosActivity extends AppCompatActivity implements DetalhesServicoListener {


    private TextView etNomeServico, etTaxaIva, etPrecoServico;
    private Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servicos);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int id = getIntent().getIntExtra("ID_SERVICO", 0);
        servico = SingletonGestorApp.getInstance(getApplicationContext()).getServico(id);

        etNomeServico = findViewById(R.id.etNomeServico);
        //etTaxaIva = findViewById(R.id.etTaxaIva);
        etPrecoServico = findViewById(R.id.etPrecoServico);


        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesServicoListener(this);

        if (servico != null) {
            carregarServico();
        }

    }

    private void carregarServico() {
        etNomeServico.setText(servico.getNome());
        //etTaxaIva.setText(servico.getIva_id());
        etPrecoServico.setText(String.valueOf(servico.getPreco()));

    }

    @Override
    public void onRefreshDetalhes(int operacao) {
        Intent intent = new Intent();
        intent.putExtra(MenuMainActivity.OPERACAO,operacao);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
