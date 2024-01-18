package com.example.dentalcare.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="dentalcare";
    private static final int DB_VERSION=1;

    private static final String TABLE_PRODUTOS ="produtos";
    private final SQLiteDatabase db;

    private static final String ID = "id",

    NOME = "nome", DESCRICAO ="descricao", PRECOUNITARIO="precounitario";


    public BDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableProdutos = "CREATE TABLE "+TABLE_PRODUTOS+"("+ID+" INTEGER PRIMARY KEY,"+
                NOME+" TEXT NOT NULL,"+
                DESCRICAO+" TEXT NOT NULL,"+
                PRECOUNITARIO+" DOUBLE NOT NULL);";
        sqLiteDatabase.execSQL(createTableProdutos);

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

     */
}
