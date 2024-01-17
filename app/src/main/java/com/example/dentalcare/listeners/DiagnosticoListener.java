package com.example.dentalcare.listeners;

import com.example.dentalcare.models.Diagnostico;

import java.util.ArrayList;

public interface DiagnosticoListener {
    void onRefreshListaDiagnosticos(ArrayList<Diagnostico> listadiagnosticos);
}
