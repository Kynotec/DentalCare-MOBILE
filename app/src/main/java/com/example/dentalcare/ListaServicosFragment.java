package com.example.dentalcare;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;


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

    private ListaServicosAdaptador servicosAdapter;

    private SearchView searchView;
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
                intent.putExtra(DetalhesServicosActivity.ID_SERVICO, (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });

        // Inicialize o adaptador aqui e defina no ListView
        servicosAdapter = new ListaServicosAdaptador(getContext(), new ArrayList<>());
        lvservicos.setAdapter(servicosAdapter);

        SingletonGestorApp.getInstance(getContext()).setServicosListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllServicosAPI(getContext());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        // Encontrar o item de pesquisa no menu
        MenuItem itemSearch = menu.findItem(R.id.itemPesquisa);

        // Verificar se o item de pesquisa não é nulo
        if (itemSearch != null) {
            // Obter o SearchView diretamente do item de pesquisa
            searchView = (SearchView) itemSearch.getActionView();

            // Adicionar um listener de consulta de texto
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    // Ação a ser tomada ao submeter a consulta
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    // Ação a ser tomada quando o texto da consulta é alterado
                    ArrayList<Servico> tempLista = new ArrayList<>();
                    ArrayList<Servico> teste = SingletonGestorApp.getInstance(getContext()).getServicosBD();
                    for (Servico a : teste) {
                        if (a.getNome().toLowerCase().contains(s.toLowerCase())) {
                            tempLista.add(a);
                        }
                    }

                    // Criar um novo adaptador com a lista filtrada
                    ListaServicosAdaptador newAdapter = new ListaServicosAdaptador(getContext(), tempLista);
                    lvservicos.setAdapter(newAdapter);

                    return true;
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        if (searchView != null) {
            searchView.onActionViewCollapsed();
        }
        super.onResume();
    }

    @Override
    public void onRefreshListaServicos(ArrayList<Servico> listaservicos) {
        if (listaservicos != null) {
            // Criar um novo adaptador e definir na ListView
            servicosAdapter = new ListaServicosAdaptador(getContext(), listaservicos);
            lvservicos.setAdapter(servicosAdapter);
        }
    }
}
