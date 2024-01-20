package com.example.dentalcare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dentalcare.listeners.DetalhesProdutoListener;
import com.example.dentalcare.listeners.DetalhesServicoListener;
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.models.Carrinho;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;
import com.example.dentalcare.utils.JsonParser;

import java.util.ArrayList;

public class DetalhesProdutosActivity extends AppCompatActivity implements DetalhesProdutoListener {


    private TextView etNomeProduto,tvStock, etDescricaoProduto, etPrecoUnitarioProduto;
    private Produto produto;

    private ImageView imgCapa;
    private ImageButton btnMore, btnMinus;
    private Button btnCart;

    private EditText numQuant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produtos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (!JsonParser.isConnectionInternet(this)) {
            Toast.makeText(this, R.string.sem_ligacao, Toast.LENGTH_SHORT).show();
            finish();
        }

        int id = getIntent().getIntExtra("ID_PRODUTO", 0);
        produto = SingletonGestorApp.getInstance(getApplicationContext()).getProduto(id);

        etNomeProduto = findViewById(R.id.etNomeProduto);
        etDescricaoProduto = findViewById(R.id.etDescricaoProduto);
        etPrecoUnitarioProduto = findViewById(R.id.etPrecoUnitarioProduto);
        imgCapa = findViewById(R.id.imgProduto);


        tvStock = findViewById(R.id.tvStock);
        btnCart = findViewById(R.id.btnAddCart);
        btnMinus = findViewById(R.id.btnMinus);
        btnMore = findViewById(R.id.btnMore);
        numQuant = findViewById(R.id.numQuantity);
        SingletonGestorApp.getInstance(getApplicationContext()).setDetalhesProdutoListener(this);

        if (produto != null) {
            carregarProduto();
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        Intent i = null;

        switch (itemId) {
            case android.R.id.home:

             return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

     */

    private void carregarProduto() {
        etNomeProduto.setText(produto.getNome());
        etDescricaoProduto.setText(produto.getDescricao());
        etPrecoUnitarioProduto.setText(String.format("%.2f€", produto.getPrecounitario()));
        if (produto.getStock() != 0) {
            tvStock.setText(R.string.emStock);
            tvStock.setTextColor(Color.parseColor("#048000"));
        } else {
            tvStock.setText(R.string.foraStock);
            btnCart.setEnabled(false);
            tvStock.setTextColor(Color.parseColor("#b00200"));
            btnMore.setEnabled(false);
            btnMinus.setEnabled(false);
            numQuant.setEnabled(false);
        }

        Glide.with(getApplicationContext())
                .load(produto.getImagem())
                .placeholder(R.drawable.dentalcare_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgCapa);

    }

    @Override
    public void onRefreshDetalhes(int operacao) {
        Intent intent = new Intent();
        intent.putExtra(MenuMainActivity.OPERACAO,operacao);
        setResult(RESULT_OK,intent);
        finish();
    }


    public void onCLickChangeQuantity(View view) {

        try {
            int quantity = Integer.parseInt(numQuant.getText().toString());
            int id = view.getId();

            if (id == R.id.btnMinus && quantity > 1) {
                quantity--;
            } else if (id == R.id.btnMore && quantity < 10) {
                quantity++;
            }

            numQuant.setText(String.valueOf(quantity));
        } catch (NumberFormatException e) {

            Toast.makeText(this, R.string.quantidade_stock_invalido, Toast.LENGTH_LONG).show();
        }
    }





    public void onClickAddCart(View view) {
        int quantity;
        SharedPreferences sharedPreferences = getSharedPreferences(MenuMainActivity.SHARED_USER, MODE_PRIVATE);
        if(!sharedPreferences.contains(MenuMainActivity.TOKEN)){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            return;
        }
        try {
            quantity = Integer.parseInt(numQuant.getText().toString());
        }catch (Exception e){
            Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = getIntent().getIntExtra("ID_PRODUTO", 1);
       // SingletonGestorApp.getInstance(this).addCartAPI(this, id, quantity);
    }



}