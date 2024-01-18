package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dentalcare.listeners.DetalhesFaturaListener;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.SingletonGestorApp;

public class DetalhesFaturasActivity extends AppCompatActivity implements DetalhesFaturaListener {

    private TextView tvDataFatura, tvValorTotalFatura, tvIvaTotalFatura, tvSubTotalFatura, tvEstadoFatura,tvNFatura;
    private Fatura fatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_faturas);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int id = getIntent().getIntExtra("ID_FATURA", 0);
        fatura = SingletonGestorApp.getInstance(getApplicationContext()).getFatura(id);
        tvNFatura = findViewById(R.id.tvNFatura);
        tvDataFatura = findViewById(R.id.tvDataFatura);
        tvValorTotalFatura = findViewById(R.id.tvValorTotalFatura);
        tvIvaTotalFatura = findViewById(R.id.tvIvaTotalFatura);
        tvSubTotalFatura = findViewById(R.id.tvSubTotalFatura);
        tvEstadoFatura = findViewById(R.id.tvEstadoFatura);

        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesFaturaListener(this);

        if (fatura != null) {
            carregarFatura();
        }

    }

    private void carregarFatura() {

        tvNFatura.setText(String.valueOf(fatura.getId()));
        tvDataFatura.setText(fatura.getData());
        tvValorTotalFatura.setText(String.format("%.2f€",fatura.getValortotal()));
        tvIvaTotalFatura.setText(String.format("%.2f€",fatura.getIvatotal()));
        tvSubTotalFatura.setText(String.format("%.2f€",fatura.getSubtotal()));
        tvEstadoFatura.setText(fatura.getEstado());
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