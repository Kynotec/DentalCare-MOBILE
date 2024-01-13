package com.example.dentalcare;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dentalcare.adaptadores.ListaProdutosAdaptador;
import com.example.dentalcare.listeners.ProdutosListener;
import com.example.dentalcare.models.Produto;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;


public class ListaProdutosFragment extends Fragment implements ProdutosListener {

    private ListView lvprodutos;

    private ArrayList<Produto> produtos;


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

        SingletonGestorApp.getInstance(getContext()).setProdutosListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllProdutosAPI(getContext());

        lvprodutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(getContext(), DetalhesProdutosActivity.class);
                intent.putExtra(DetalhesProdutosActivity.ID_PRODUTO, (int) id);

               // startActivityForResult(intent,MenuMainActivity.EDIT);
            }
        });
        /*
        fabLista = view.findViewById(R.id.fabLista);
        fabLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetalhesLivroActivity.class);
                // startActivity(intent);
                startActivityForResult(intent,MenuMainActivity.ADD);
            }
        });

         */
        return view;
    }

    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos !=null)
            lvprodutos.setAdapter(new ListaProdutosAdaptador(getContext(),listaProdutos));

    }
}