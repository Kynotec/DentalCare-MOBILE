package com.example.dentalcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dentalcare.listeners.PerfilListener;
import com.example.dentalcare.models.Perfil;
import com.example.dentalcare.models.SingletonGestorApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class EditPerfilFragment extends Fragment implements PerfilListener {


    private NavigationView navigationView;
    private Perfil perfils;
    private String token;
    private FloatingActionButton fabGuardar;
    public static final String SHARED_USER = "DADOS_USER"; // CHAVE

    private EditText etNomeUtente, etTelefoneUtente, etMoradaUtente, etNIFUtente, etCodigoPostalUtente;

    public static final int MAX_CHAR = 55, MIN_CHAR = 2;


    public EditPerfilFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_perfil, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        etNomeUtente = view.findViewById(R.id.etNomeUtente);
        etTelefoneUtente = view.findViewById(R.id.etTelefoneUtente);
        etNIFUtente = view.findViewById(R.id.etNIFUtente);
        etMoradaUtente = view.findViewById(R.id.etMoradaUtente);
        etCodigoPostalUtente = view.findViewById(R.id.etCodPostalUtente);
        fabGuardar = view.findViewById(R.id.fabGuardar);


        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(isPerfilValido()) {
                        perfils.setNome(etNomeUtente.getText().toString());
                        perfils.setTelefone(Integer.parseInt(etTelefoneUtente.getText().toString()));
                        perfils.setNif(Integer.parseInt(etNIFUtente.getText().toString()));
                        perfils.setMorada(etMoradaUtente.getText().toString());
                        perfils.setCodigopostal(etCodigoPostalUtente.getText().toString());


                        View headerView = ((MenuMainActivity) getActivity()).navigationView.getHeaderView(0);
                        TextView tvUsername = headerView.findViewById(R.id.tvMainUsername); // Para ir buscar ao cabeçalho do navigation view
                        tvUsername.setText(etNomeUtente.getText().toString());

                        SingletonGestorApp.getInstance(getContext()).editPerfilAPI(perfils, getContext(), token);

                         //Volta ao perfil
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.contentFragment, new PerfilFragment());
                        fr.commit();
                        //Fecha o teclado
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        Toast.makeText(getContext(), "Perfil editado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Corrija os erros", Toast.LENGTH_SHORT).show();
                        //Fecha o teclado
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                } catch (Exception e)
                {
                    Toast.makeText(getContext(),"Máximo 9 digitos",Toast.LENGTH_LONG).show();
                }
            }
        });
        SingletonGestorApp.getInstance(getContext()).setPerfilListener(this);
        SingletonGestorApp.getInstance(getContext()).getPerfilAPI(getContext(), token);

        return view;
    }

    @Override
    public void onShowPerfil(Perfil perfil) {
        if (perfil != null) {
            etNomeUtente.setText(perfil.getNome());
            etTelefoneUtente.setText(String.valueOf(perfil.getTelefone()));
            etNIFUtente.setText(String.valueOf(perfil.getNif()));
            etMoradaUtente.setText(perfil.getMorada());
            etCodigoPostalUtente.setText(perfil.getCodigopostal());

            perfils = perfil;
        }
    }

    //Verificar se o Perfil é válido caso seja envia true
    private boolean isPerfilValido() {
        String nome = etNomeUtente.getText().toString();
        int telemovel = Integer.parseInt(etTelefoneUtente.getText().toString());
        int nif = Integer.parseInt(etNIFUtente.getText().toString());
        String morada = etMoradaUtente.getText().toString();
        String codpostal = etCodigoPostalUtente.getText().toString();


        if (nome.length() > MAX_CHAR || nome.length() < MIN_CHAR) {
            etNomeUtente.setError("Nome inválido");
            return false;
        }
        if (!(telemovel >= 100000000 && telemovel <= 999999999)) {
            etTelefoneUtente.setError("Telefone inválido");
            return false;
        }

        if (!(nif >= 100000000 && nif <= 999999999)) {
            etNIFUtente.setError("NIF inválido");
            return false;
        }
        if (morada.length() > MAX_CHAR || morada.length() < MIN_CHAR) {
            etMoradaUtente.setError("Morada Inválida");
            return false;
        }
        if (codpostal.length() > 8) {
            etCodigoPostalUtente.setError("Código Postal Inválido");
            return false;
        }

        return true;
    }

}