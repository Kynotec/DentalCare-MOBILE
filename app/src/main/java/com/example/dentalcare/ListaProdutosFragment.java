package com.example.dentalcare;

import static com.example.dentalcare.ListaServicosFragment.ACT_DETAlHES;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;


public class ListaProdutosFragment extends Fragment implements ProdutosListener {

    private ListView lvprodutos;

    private ArrayList<Produto> produtos;

    private SearchView searchView;

    public ListaProdutosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_produtos, container, false);
        setHasOptionsMenu(true);
        lvprodutos = view.findViewById(R.id.lvprodutos);

        lvprodutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(getContext(), DetalhesProdutosActivity.class);
                intent.putExtra("ID_PRODUTO", (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });

        SingletonGestorApp.getInstance(getContext()).setProdutosListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllProdutosAPI(getContext());

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
                    ArrayList<Produto> tempLista = new ArrayList<>();
                    ArrayList<Produto> teste = SingletonGestorApp.getInstance(getContext()).getProdutosBD();
                    for (Produto p : teste) {
                        if (p.getNome().toLowerCase().contains(s.toLowerCase())) {
                            tempLista.add(p);
                        }
                    }

                    // Criar um novo adaptador com a lista filtrada
                    ListaProdutosAdaptador newAdapter = new ListaProdutosAdaptador(getContext(), tempLista);
                    lvprodutos.setAdapter(newAdapter);

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
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {
        if (listaProdutos != null)
            lvprodutos.setAdapter(new ListaProdutosAdaptador(getContext(), listaProdutos));
    }
}