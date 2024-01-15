package com.example.dentalcare.listeners;

import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;

import java.util.ArrayList;

public interface ServicosListener {

    void onRefreshListaServicos(ArrayList<Servico> listaservicos);

}
