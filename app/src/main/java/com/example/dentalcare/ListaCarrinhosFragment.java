package com.example.dentalcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.dentalcare.adaptadores.ListaLinhaCarrinhoAdaptador;
import com.example.dentalcare.listeners.LinhaCarrinhoListener;
import com.example.dentalcare.models.Linha_carrinho;
import com.example.dentalcare.models.Linha_fatura;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ListaCarrinhosFragment extends Fragment implements LinhaCarrinhoListener {

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

        btnCheckOut.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingletonGestorApp.getInstance(getContext()).getPagamentoAPI(getContext());

            }


        });
        return view;
    }

    @Override
    public void onRefreshLinhaCarrinho(ArrayList<Linha_carrinho> linha_carrinhos) {
        if (linha_carrinhos != null){
            lvlinhascarrinho.setAdapter(new ListaLinhaCarrinhoAdaptador(getContext(),linha_carrinhos));
        }

    }
}