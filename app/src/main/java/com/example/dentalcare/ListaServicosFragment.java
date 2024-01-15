package com.example.dentalcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dentalcare.adaptadores.ListaProdutosAdaptador;
import com.example.dentalcare.adaptadores.ListaServicosAdaptador;
import com.example.dentalcare.listeners.ServicosListener;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaServicosFragment extends Fragment implements ServicosListener {

    private ListView lvservicos;

    public static final int ACT_DETAlHES =1;

    public ListaServicosFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_servicos, container, false);
        setHasOptionsMenu(true);

        lvservicos = view.findViewById(R.id.lvservicos);
        lvservicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesServicosActivity.class);
                intent.putExtra("ID_SERVICO", (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });


        SingletonGestorApp.getInstance(getContext()).setServicosListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllServicosAPI(getContext());

        return view;
    }


    @Override
    public void onRefreshListaServicos(ArrayList<Servico> listaservicos) {
        if(listaservicos !=null)
            lvservicos.setAdapter(new ListaServicosAdaptador(getContext(),listaservicos));
    }
}