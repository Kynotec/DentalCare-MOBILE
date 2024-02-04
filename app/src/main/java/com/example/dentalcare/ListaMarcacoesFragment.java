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
import com.example.dentalcare.adaptadores.ListaMarcacoesAdaptador;
import com.example.dentalcare.listeners.MarcacaoListener;
import com.example.dentalcare.models.Consulta;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListaMarcacoesFragment extends Fragment implements MarcacaoListener {
    private ListView lvmarcacoes;

    private String token;
    public static final int ACT_DETAlHES =1;

    private FloatingActionButton fabLista;

    public ListaMarcacoesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_marcacoes, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        lvmarcacoes = view.findViewById(R.id.lvmarcacoes);

        SingletonGestorApp.getInstance(getContext()).setMarcacoesListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllMarcacoesAPI(getContext(), token);

        lvmarcacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesMarcacoesActivity.class);
                intent.putExtra("ID_MARCACAO", (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });
        fabLista = view.findViewById(R.id.fabLista);
        fabLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetalhesMarcacoesActivity.class);
                // startActivity(intent);
                startActivityForResult(intent,MenuMainActivity.ADD);
            }
        });
        return view;
    }
    @Override
    public void onRefreshListaMarcacoes(ArrayList<Consulta> listamarcacoes) {
        if(listamarcacoes !=null)
            lvmarcacoes.setAdapter(new ListaMarcacoesAdaptador(getContext(),listamarcacoes));
    }
}