package com.example.dentalcare.listeners;


import com.example.dentalcare.models.Fatura;

import java.util.ArrayList;

public interface FaturaListener {

    void onRefreshListaFaturas(ArrayList<Fatura> listafaturas);
}
