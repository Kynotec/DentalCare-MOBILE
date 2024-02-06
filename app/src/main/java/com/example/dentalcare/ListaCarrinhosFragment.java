package com.example.dentalcare;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dentalcare.adaptadores.ListaLinhaCarrinhoAdaptador;
import com.example.dentalcare.listeners.LinhaCarrinhoListener;
import com.example.dentalcare.listeners.TemLinhaCarrinhoListener;
import com.example.dentalcare.models.Linha_carrinho;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListaCarrinhosFragment extends Fragment implements LinhaCarrinhoListener, TemLinhaCarrinhoListener {

    private ListView lvlinhascarrinho;

    private ArrayList<Linha_carrinho> linha_carrinhos;

    private FloatingActionButton btnCheckOut;

    public ListaCarrinhosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_carrinhos , container , false);
        setHasOptionsMenu(true);
        lvlinhascarrinho = view.findViewById(R.id.lvlinhascarrinho);
        btnCheckOut = view.findViewById(R.id.btnCheckOut);
        SingletonGestorApp.getInstance(getContext()).setLinhaCarrinhoListener(this);
        SingletonGestorApp.getInstance(getContext()).getViewCarrinhoAPI(getContext());
        SingletonGestorApp.getInstance(getContext()).setTemitemslinhacarrinho(this);
        if (lvlinhascarrinho == null) {
            // Se não houver linhas de carrinho
            showToast("Não existem produtos no carrinho!");
        }
        btnCheckOut.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {
                if (lvlinhascarrinho != null) {
                SingletonGestorApp.getInstance(getContext()).getPagamentoAPI(getContext());
                } else {
                    // Se não houver linhas de carrinho
                    showToast("Não existem produtos no carrinho!");
                }
            }
        });
        return view;
    }


    private void showToast(String message) {
        Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshLinhaCarrinho(ArrayList<Linha_carrinho> linha_carrinhos) {
        if (linha_carrinhos != null && !linha_carrinhos.isEmpty()) {
            // Se houver linhas de carrinho
            lvlinhascarrinho.setAdapter(new ListaLinhaCarrinhoAdaptador(getContext(), linha_carrinhos));
        } else {
            // Se não houver linhas de carrinho
            showToast("Não existem produtos no carrinho!");
        }
    }

    @Override
    public void onRefreshLinhaCarrinho(boolean temlinhacarrinho) {
        if (temlinhacarrinho) {
            if (lvlinhascarrinho.getAdapter() instanceof ListaLinhaCarrinhoAdaptador) {
                ListaLinhaCarrinhoAdaptador adaptador = (ListaLinhaCarrinhoAdaptador) lvlinhascarrinho.getAdapter();
                adaptador.clearCarrinho(); // método clearCarrinho() no adaptador para limpar o carrinho
                adaptador.notifyDataSetChanged();
            }

        }
    }

}