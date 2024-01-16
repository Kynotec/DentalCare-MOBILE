package com.example.dentalcare;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
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

public class PerfilFragment extends Fragment implements PerfilListener {

    private Perfil perfils;
    private String token;
    private FragmentManager fragmentManager;
    private FloatingActionButton fabEditar;
    private EditText etNome, etTelefone, etMorada, etNif, etCodigoPostal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
       setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        etNome = view.findViewById(R.id.etNome);
        etTelefone = view.findViewById(R.id.etTelefone);
        etMorada = view.findViewById(R.id.etMorada);
        etNif = view.findViewById(R.id.etNif);
        etCodigoPostal = view.findViewById(R.id.etCodigoPostal);
       // fabEditar = view.findViewById(R.id.fabEditar);


        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        SingletonGestorApp.getInstance(getContext()).setPerfilListener(this);
        SingletonGestorApp.getInstance(getContext()).getPerfilAPI(getContext(), token);

        return view;
    }

    //Mostra o perfil com os dados do cliente
    @Override
    public void onShowPerfil(Perfil perfil) {

        if (perfil != null) {
            etNome.setText(perfil.getNome());
            etTelefone.setText(String.valueOf(perfil.getTelefone()));
            etNif.setText(String.valueOf(perfil.getNif()));
            etMorada.setText(perfil.getMorada());
            etCodigoPostal.setText(perfil.getCodigopostal());

            perfils = perfil;
        }
    }


}