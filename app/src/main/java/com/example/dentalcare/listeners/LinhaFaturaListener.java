package com.example.dentalcare.listeners;


import com.example.dentalcare.models.Linha_fatura;

import java.util.ArrayList;

public interface LinhaFaturaListener {

    void onRefreshLinhaFaturas(ArrayList<Linha_fatura> linha_faturas);
}
