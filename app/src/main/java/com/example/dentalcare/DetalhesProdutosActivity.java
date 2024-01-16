package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dentalcare.listeners.DetalhesProdutoListener;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;

public class DetalhesProdutosActivity extends AppCompatActivity implements DetalhesProdutoListener {


    private TextView etNomeProduto, etDescricaoProduto, etPrecoUnitarioProduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produtos);

        int id = getIntent().getIntExtra("ID_PRODUTO", 0);
        produto = SingletonGestorApp.getInstance(getApplicationContext()).getProduto(id);

        etNomeProduto = findViewById(R.id.etNomeProduto);
        etDescricaoProduto = findViewById(R.id.etDescricaoProduto);
        etPrecoUnitarioProduto = findViewById(R.id.etPrecoUnitarioProduto);


        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesProdutoListener(this);

        if (produto != null) {
            carregarProduto();
        }

    }

    private void carregarProduto() {
        etNomeProduto.setText(produto.getNome());
        etDescricaoProduto.setText(produto.getDescricao());
        etPrecoUnitarioProduto.setText(String.valueOf(produto.getPrecounitario()));

    }

    @Override
    public void onRefreshDetalhes(int operacao) {
        Intent intent = new Intent();
        intent.putExtra(MenuMainActivity.OPERACAO,operacao);
        setResult(RESULT_OK,intent);
        finish();
    }
}