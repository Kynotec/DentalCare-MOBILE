package com.example.dentalcare.listeners;

import com.example.dentalcare.models.Produto;

import java.util.ArrayList;

public interface ProdutosListener {

    void onRefreshListaProdutos(ArrayList<Produto> listaprodutos);
}
