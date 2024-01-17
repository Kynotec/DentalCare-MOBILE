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
import com.example.dentalcare.adaptadores.ListaServicosAdaptador;
import com.example.dentalcare.listeners.DiagnosticoListener;
import com.example.dentalcare.models.Diagnostico;
import com.example.dentalcare.models.Servico;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.ArrayList;

public class ListaDiagnosticosFragment extends Fragment implements DiagnosticoListener {

    private ListView lvdiagnosticos;

    private String token;
    public static final int ACT_DETAlHES =1;

    public ListaDiagnosticosFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_diagnostico, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        lvdiagnosticos = view.findViewById(R.id.lvdiagnosticos);
        lvdiagnosticos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesDiagnosticosActivity.class);
                intent.putExtra("ID_DIAGNOSTICO", (int) id);
                startActivityForResult(intent, ACT_DETAlHES);
            }
        });

        SingletonGestorApp.getInstance(getContext()).setDiagnosticosListener(this);
        SingletonGestorApp.getInstance(getContext()).getAllDiagnosticosAPI(getContext(), token);

        return view;
    }
    @Override
    public void onRefreshListaDiagnosticos(ArrayList<Diagnostico> listadiagnosticos) {
        if(listadiagnosticos !=null)
            lvdiagnosticos.setAdapter(new ListaDiagnosticosAdaptador(getContext(),listadiagnosticos));
    }

}