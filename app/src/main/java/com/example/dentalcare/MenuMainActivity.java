package com.example.dentalcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



public class MenuMainActivity extends AppCompatActivity {
    public static final String USERNAME = "USER"; // NOME
    public static final String TOKEN = "TOKEN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
    }
}