package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dentalcare.listeners.DetalhesDiagnosticoListener;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;

public class DetalhesDiagnosticosActivity extends AppCompatActivity  implements DetalhesDiagnosticoListener {

    private TextView etDataDiagnostico, etHoraDiagnostico, etDescricaoDiagnostico, etDataConsulta;
    private Diagnostico diagnostico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_diagnosticos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra("ID_DIAGNOSTICO", 0);
        diagnostico = SingletonGestorApp.getInstance(getApplicationContext()).getDiagnostico(id);

        etDataDiagnostico = findViewById(R.id.etDataDiagnostico);
        etHoraDiagnostico = findViewById(R.id.etHoraDiagnostico);
        etDescricaoDiagnostico = findViewById(R.id.etDescricaoDiagnostico);
        etDataConsulta  = findViewById(R.id.etDataConsulta);

        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesDiagnosticoListener(this);

        if (diagnostico != null) {
            carregarDiagnostico();
        }

    }

    private void carregarDiagnostico() {
        etDataDiagnostico.setText(diagnostico.getData());
        etHoraDiagnostico.setText(diagnostico.getHora());
        etDescricaoDiagnostico.setText(diagnostico.getDescricao());
        //etDataConsulta.setText(diagnostico.getConsulta_id());

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
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}