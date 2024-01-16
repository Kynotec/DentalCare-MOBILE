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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dentalcare.listeners.PerfilListener;
import com.example.dentalcare.models.Perfil;
import com.example.dentalcare.models.SingletonGestorApp;

public class PerfilFragment extends Fragment implements PerfilListener {

    private Perfil perfil;
    private String token;
    private FragmentManager fragmentManager;

    private TextView tvNome, tvTelefone, tvMorada, tvNif, tvCodigoPostal, tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        setHasOptionsMenu(true);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(MenuMainActivity.SHARED_USER, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(MenuMainActivity.TOKEN, null);

        tvNome = view.findViewById(R.id.etNome);
        tvTelefone = view.findViewById(R.id.etTelefone);
        tvMorada = view.findViewById(R.id.etMorada);
        tvNif = view.findViewById(R.id.etNif);
        tvCodigoPostal = view.findViewById(R.id.etCodigoPostal);
        tvEmail = view.findViewById(R.id.etEmail);


        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        SingletonGestorApp.getInstance(getContext()).setPerfilListener(this);
        //SingletonGestorApp.getInstance(getContext()).getPerfilAPI(getContext(), token);

        return view;
    }

    //Mostra o perfil com os dados do cliente
    @Override
    public void onMostrarPerfil(Perfil perfil) {
        if (perfil != null) {

        }
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}