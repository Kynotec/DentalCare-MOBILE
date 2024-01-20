package com.example.dentalcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.dentalcare.adaptadores.ListaFaturasAdaptador;
import com.example.dentalcare.adaptadores.ListaLinhaFaturasAdaptador;
import com.example.dentalcare.listeners.LinhaFaturaListener;
import com.example.dentalcare.models.Fatura;
import com.example.dentalcare.models.Linha_fatura;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;

public class ListaLinhaFaturasFragment extends Fragment implements LinhaFaturaListener {

    private ListView lvlinhafaturas;
    private String token;

    public ListaLinhaFaturasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_linhafaturas, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);
        lvlinhafaturas = view.findViewById(R.id.lvlinhafaturas);
        SingletonGestorApp.getInstance(getContext()).setLinhaFaturasListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllLinhaFaturasAPI(getContext(), token);

        return view;
    }



    @Override
    public void onRefreshLinhaFaturas(ArrayList<Linha_fatura> linha_faturas) {
        if (linha_faturas != null)
            lvlinhafaturas.setAdapter(new ListaLinhaFaturasAdaptador(getContext(), linha_faturas));
    }

}
