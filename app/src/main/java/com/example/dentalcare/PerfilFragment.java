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
import com.example.dentalcare.utils.JsonParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilFragment extends Fragment implements PerfilListener {

    private Perfil perfils;
    private String token;
    private FloatingActionButton fabEditar;
    private TextView tvNomeUntente, tvTelemovelUtente, tvNIFUtente, tvMoradaUtente, tvCodigoPostalUtente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
       setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        tvNomeUntente = view.findViewById(R.id.tvNomeUntente);
        tvTelemovelUtente = view.findViewById(R.id.tvTelemovelUtente);
        tvNIFUtente = view.findViewById(R.id.tvNIFUtente);
        tvMoradaUtente = view.findViewById(R.id.tvMoradaUtente);
        tvCodigoPostalUtente = view.findViewById(R.id.tvCodigoPostalUtente);
       fabEditar = view.findViewById(R.id.edit);

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!JsonParser.isConnectionInternet(getContext())){
                    Toast.makeText(getContext(), getContext().getString(R.string.sem_ligacao), Toast.LENGTH_SHORT).show();
                }else {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.contentFragment, new EditPerfilFragment());
                    fr.commit();
                }
            }
        });

        SingletonGestorApp.getInstance(getContext()).setPerfilListener(this);
        SingletonGestorApp.getInstance(getContext()).getPerfilAPI(getContext(), token);

        return view;
    }

    //Mostra o perfil com os dados do cliente
    @Override
    public void onShowPerfil(Perfil perfil) {

        if (perfil != null) {
            tvNomeUntente.setText(perfil.getNome());
            tvTelemovelUtente.setText(String.valueOf(perfil.getTelefone()));
            tvNIFUtente.setText(String.valueOf(perfil.getNif()));
            tvMoradaUtente.setText(perfil.getMorada());
            tvCodigoPostalUtente.setText(perfil.getCodigopostal());

            perfils = perfil;
        }
    }


}