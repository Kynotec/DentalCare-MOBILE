package com.example.dentalcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dentalcare.adaptadores.ListaDiagnosticosAdaptador;
import com.example.dentalcare.adaptadores.ListaFaturasAdaptador;
import com.example.dentalcare.listeners.FaturaListener;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;


public class ListaFaturasFragment extends Fragment implements FaturaListener {

    private ListView lvfaturas;

    private String token;

    public static final int ACT_DETAlHES = 1;


    public ListaFaturasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_faturas, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
        lvfaturas = view.findViewById(R.id.lvfaturas);
        lvfaturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesFaturasActivity.class);
                intent.putExtra("ID_FATURA", (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });

        SingletonGestorApp.getInstance(getContext()).setFaturasListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllFaturasAPI(getContext(), token);

        return view;
    }

    @Override
    public void onRefreshListaFaturas(ArrayList<Fatura> listafaturas) {
        if (listafaturas != null)
            lvfaturas.setAdapter(new ListaFaturasAdaptador(getContext(), listafaturas));
    }
}