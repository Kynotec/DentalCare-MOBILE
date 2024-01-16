package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dentalcare.listeners.LoginListener;
import com.example.dentalcare.models.SingletonGestorApp;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private EditText etUsername, etPassword;
    private final int MIN_PASS = 8;

    public static final String USERNAME="USERNAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Locale locale = new Locale("pt_PT");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getApplicationContext().getResources().updateConfiguration(config, null);

        SingletonGestorApp.getInstance(getApplicationContext()).setLoginListener(this);

        // Atribuir as editText ás variaveis para poder acessar
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    // validar o user e o login e apresentar o resultado da validação num Toast.
    public void onClickLogin(View view) {

        // Ir buscar o email e password introduzidos
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // se o email for valido
        if (!isUsernameValid(username)) {
            etUsername.setText(R.string.txt_username_invalido);
            return;
        }
        // se a password for valida
        if (!isPasswordValid(password)) {
            etPassword.setError(getString(R.string.txt_password_invalida));
            return;
        }

        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        SingletonGestorApp.getInstance(getApplicationContext()).loginAPI(username, password, getApplicationContext());

    }

    // Verifica se o username introduzido é válido
    private boolean isUsernameValid(String username) {
        if (username == null)
            return false;
        return true;

    }

    // Verifica se a password introduzida é válida
    private boolean isPasswordValid(String pass) {
        if (pass == null)
            return false;
        return pass.length() >= MIN_PASS; // verifica se tem no minimo 8 caracteres (true)
    }

    // Iniciar atividade do menu depois do login

    @Override
    public void onValidateLogin(String token, String username, Context context) {
        if (token != null) {
            // Salvar o nome de usuário nas SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorUser = sharedPreferences.edit();
            editorUser.putString(MenuMainActivity.USERNAME, username);
            editorUser.putString(MenuMainActivity.TOKEN, token);
            editorUser.apply();

            // Iniciar a MenuMainActivity
            Intent intent = new Intent(context, MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.USERNAME, username);
            intent.putExtra(MenuMainActivity.TOKEN, token);
            startActivity(intent);
            finish();  // Finalizar LoginActivity, se necessário
        } else {
            Toast.makeText(context, "Erro login", Toast.LENGTH_LONG).show();
        }

    }


}