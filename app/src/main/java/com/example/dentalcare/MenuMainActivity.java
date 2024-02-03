package com.example.dentalcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;


public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String token;
    private String username;

    public static final String OPERACAO ="OPERACAO";

    public static final int ADD =100, EDIT =200, DELETE = 300;

    public NavigationView navigationView;
    private DrawerLayout drawer;
    public static final String USERNAME = "USER"; // NOME
    public static final String SHARED_USER = "DADOS_USER"; // CHAVE
    public static final String PASSWORD = "PASSWORD";
    public static final String TOKEN = "TOKEN";
    private FragmentManager fragmentManager;

    public static final String EMAIL="EMAIL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        carregarCabecalho();

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        carregarFragmentoInicial();
    }

    private boolean carregarFragmentoInicial() {
        Menu menu = navigationView.getMenu();
        MenuItem item=menu.getItem(0);
        item.setChecked(true);
        return onNavigationItemSelected(item);
    }


    private void carregarCabecalho() {
        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.USERNAME);
        token = getIntent().getStringExtra(TOKEN);
        SharedPreferences sharedPreferencesUsernameUser = getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE);

        if (username != null && token != null) {
            SharedPreferences.Editor editorUser = sharedPreferencesUsernameUser.edit();
            editorUser.putString(USERNAME, username);
            editorUser.putString(TOKEN, token);
            editorUser.apply();
        } else{
            username = sharedPreferencesUsernameUser.getString(USERNAME, "Sem username");
        }
        // header do navigation
        View hView=navigationView.getHeaderView(0);
        //através do hView já consigo aceder aos componentes do header (nav_header_main)
        TextView tvMainUsername=hView.findViewById(R.id.tvMainUsername);
        tvMainUsername.setText(username);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        if (item.getItemId() == R.id.navItemProdutos) {
            fragment = new ListaProdutosFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Produtos");
        }
        else if (item.getItemId() == R.id.navItemServicos) {
            fragment = new ListaServicosFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Servicos");
        }
        else if (item.getItemId() == R.id.navItemLogout) {
            showLogoutConfirmationDialog();
            return true;
        }
        else if (item.getItemId() == R.id.navItemDadosUtente) {
            fragment = new PerfilFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Dados Utente");
        }
        else if (item.getItemId() == R.id.navItemDiagnosticos) {
            fragment = new ListaDiagnosticosFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Diagnosticos Utente");
        }
        else if (item.getItemId() == R.id.navItemFaturas) {
            fragment = new ListaFaturasFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Faturas");
        }
        else if (item.getItemId() == R.id.navItemCarrinho) {
            fragment = new ListaCarrinhosFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Carrinho");
        }
        else if (item.getItemId() == R.id.navItemListaMarcacoes) {
            fragment = new ListaMarcacoesFragment();
            setTitle(item.getTitle());
            System.out.println("-->Nav Marcacoes");
        }
        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Terminar Sessão");
        builder.setMessage("Tem certeza que deseja terminar a sessão?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logoutUser();
            }
        });


        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.RED);
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            }
        });

        alertDialog.show();
    }

    private void logoutUser() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}