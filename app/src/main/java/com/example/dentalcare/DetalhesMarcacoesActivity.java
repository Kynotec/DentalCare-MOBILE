package com.example.dentalcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dentalcare.listeners.DetalhesMarcacaoListener;
import com.example.dentalcare.models.Consulta;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DetalhesMarcacoesActivity extends AppCompatActivity  implements DetalhesMarcacaoListener {

    private TextView etDescricao, etDataMarcacao, etHoraMarcacao, etEstadoMarcacao, etNomeServico;
    private FloatingActionButton fabGuardar;
    private Consulta marcacao;

    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_marcacoes);

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDescricao = findViewById(R.id.etDescricaoMarcacao);
        etDataMarcacao = findViewById(R.id.etDataDescricao);
        etHoraMarcacao = findViewById(R.id.etHoraMarcacao);
        etNomeServico  = findViewById(R.id.etNomeServicoMarcacao);
        fabGuardar = findViewById(R.id.fabSave);

        SingletonGestorApp.getInstance(getApplicationContext()).setMarcacaoListener(this);

        int id = getIntent().getIntExtra("ID_MARCACAO", 0);
        if (id != 0) {
            marcacao = SingletonGestorApp.getInstance(getApplicationContext()).getMarcacao(id);
            if (marcacao != null) {
                carregarMarcacoes();
                fabGuardar.setImageResource(R.drawable.ic_action_save);
            } else
                finish();
        } else {
            setTitle("Adicionar Marcação");
            fabGuardar.setImageResource(R.drawable.ic_action_add);
        }

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (marcacao != null) {
                    marcacao.setDescricao(etDescricao.getText().toString());
                    marcacao.setData(etDataMarcacao.getText().toString());
                    marcacao.setHora(etHoraMarcacao.getText().toString());
                    marcacao.setNomeservico(etNomeServico.getText().toString());

                    SingletonGestorApp.getInstance(getApplicationContext()).editarMarcacaoAPI(marcacao,getApplicationContext(),token);
                } else {
                    /*
                    marcacao = new Consulta(0,DEFAULT_IMG,Integer.parseInt(etAno.getText().toString()),etTitulo.getText().toString(),etSerie.getText().toString(),etAutor.getText().toString());
                    SingletonGestorApp.getInstance(getApplicationContext()).adicionarLivroAPI(livro,getApplicationContext());
 */
                }
            }
        });

        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesMarcacaoListener(this);

        if (marcacao != null) {
            carregarMarcacoes();
        }
    }

    private void carregarMarcacoes() {
        etDescricao.setText(marcacao.getDescricao());
        etDataMarcacao.setText(marcacao.getData());
        etHoraMarcacao.setText(marcacao.getHora());
        etNomeServico.setText(marcacao.getNomeservico());
    }

    //carregar o menu pretendido
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (marcacao != null) {
            getMenuInflater().inflate(R.menu.menu_remover, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemRemover){
            SharedPreferences sharedPreferences = this.getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
            token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
            dialogRemover(token); // solicitar confirmação para removver livro
            // SingletonGestorLivros.getInstance().removerLivro(livro.getId());
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogRemover(String token) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover Marcação")
                .setMessage("Têm a certeza que pertende remover a marcação de consulta")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGestorApp.getInstance(getApplicationContext()).removerMarcacaoAPI(marcacao,getApplicationContext(),token);                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nao acontece nada
                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    @Override
    public void onRefreshDetalhes(int operacao) {
        Intent intent = new Intent();
        intent.putExtra(MenuMainActivity.OPERACAO,operacao);
        setResult(RESULT_OK,intent);
        finish();
    }

}