package com.example.dentalcare.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dentalcare";
    private static final int DB_VERSION = 1;

    private static final String TABLE_PRODUTOS = "produtos", TABLE_SERVICOS = "servicos", TABLE_CONSULTAS = "consultas",
            TABLE_DIAGNOSTICOS = "diagnosticos", TABLE_IVAS = "ivas", TABLE_LINHASFATURAS = "linhasfaturas",
            TABLE_FATURAS = "faturas";
    private final SQLiteDatabase db;

    private static final String ID = "id",
            VALORTOTAL = "valortotal", IVATOTAL = "ivatotal", SUBTOTAL = "subtotal", DATA = "data",
            QUANTIDADE = "quantidade", VALORUNITARIO = "valorunitario", VALORIVA = "valoriva", EMVIGOR = "emvigor",
            PERCENTAGEM = "percentagem", FATURA_ID = "fatura_id", PRODUTO_ID = "produto_id", SERVICO_ID = "servico_id",
            NOME = "nome", DESCRICAO = "descricao", PRECOUNITARIO = "precounitario", STOCK = "stock",
            REFERENCIA = "referencia", PRECO = "preco", IVA_ID = "iva_id", HORA = "hora",

            IMAGEM = "imagem",

            CONSULTA_ID = "consulta_id", ESTADO = "estado", PROFILE_ID = "profile_id";







    public BDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableProdutos = "CREATE TABLE " + TABLE_PRODUTOS + "(" + ID + " INTEGER PRIMARY KEY," +
                NOME + " TEXT NOT NULL," +
                DESCRICAO + " TEXT NOT NULL," +
                PRECOUNITARIO + " DOUBLE NOT NULL," +
                STOCK + " INTEGER NOT NULL," +
                IMAGEM + " STRING NOT NULL);";


        String createTableServicos = "CREATE TABLE " + TABLE_SERVICOS + "(" + ID + " INTEGER PRIMARY KEY," +
                REFERENCIA + " TEXT NOT NULL," +
                NOME + " TEXT NOT NULL," +
                DESCRICAO + " TEXT NOT NULL," +
                PRECO + " DOUBLE NOT NULL," +
                IVA_ID + " INTEGER NOT NULL );";
        /*
        String createTableIVAS = "CREATE TABLE " + TABLE_IVAS + "(" + ID + " INTEGER PRIMARY KEY," +
                EMVIGOR + " INTEGER NOT NULL," +
                PERCENTAGEM + " TEXT NOT NULL," +
                DESCRICAO + " TEXT NOT NULL );";

        String createTableDiagnosticos = "CREATE TABLE " + TABLE_DIAGNOSTICOS + "(" + ID + " INTEGER PRIMARY KEY," +
                DESCRICAO + " TEXT NOT NULL," +
                DATA + " TEXT NOT NULL," +
                HORA + " TEXT NOT NULL," +
                PROFILE_ID + " INTEGER NOT NULL," +
                CONSULTA_ID + " INTEGER NOT NULL );";


        String createTableLinhasFaturas = "CREATE TABLE " + TABLE_LINHASFATURAS + "(" + ID + " INTEGER PRIMARY KEY," +
                QUANTIDADE + " INTEGER NOT NULL," +
                VALORTOTAL + " DOUBLE NOT NULL," +
                VALORUNITARIO + " DOUBLE NOT NULL," +
                VALORIVA + " DOUBLE NOT NULL," +
                FATURA_ID + " INTEGER NOT NULL," +
                PRODUTO_ID + " INTEGER NOT NULL," +
                SERVICO_ID + " INTEGER NOT NULL );";


        String createTableFaturas = "CREATE TABLE " + TABLE_FATURAS + "(" + ID + " INTEGER PRIMARY KEY," +
                PROFILE_ID + " INTEGER NOT NULL," +
                DATA + " TEXT NOT NULL," +
                VALORTOTAL + " DOUBLE NOT NULL," +
                IVATOTAL + " DOUBLE NOT NULL," +
                SUBTOTAL + " DOUBLE NOT NULL," +
                ESTADO + " TEXT NOT NULL );";


         */

        sqLiteDatabase.execSQL(createTableProdutos);

        sqLiteDatabase.execSQL(createTableServicos);
        /*
        sqLiteDatabase.execSQL(createTableIVAS);
        sqLiteDatabase.execSQL(createTableDiagnosticos);
        sqLiteDatabase.execSQL(createTableLinhasFaturas);
        sqLiteDatabase.execSQL(createTableFaturas);

         */


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sqlDeleteTableProduto = "DROP TABLE IF EXISTS " + TABLE_PRODUTOS;
        String sqlDeleteTableServicos = "DROP TABLE IF EXISTS " + TABLE_SERVICOS;
        String sqlDeleteTableIVAS = "DROP TABLE IF EXISTS " + TABLE_IVAS;
        String sqlDeleteTableDiagnosticos = "DROP TABLE IF EXISTS " + TABLE_DIAGNOSTICOS;
        String sqlDeleteTableLinhasFaturas = "DROP TABLE IF EXISTS " + TABLE_LINHASFATURAS;
        String sqlDeleteTableFaturas = "DROP TABLE IF EXISTS " + TABLE_FATURAS;


        sqLiteDatabase.execSQL(sqlDeleteTableProduto);
        sqLiteDatabase.execSQL(sqlDeleteTableServicos);
        sqLiteDatabase.execSQL(sqlDeleteTableIVAS);
        sqLiteDatabase.execSQL(sqlDeleteTableDiagnosticos);
        sqLiteDatabase.execSQL(sqlDeleteTableLinhasFaturas);
        sqLiteDatabase.execSQL(sqlDeleteTableFaturas);

        onCreate(sqLiteDatabase);
    }


    //region CRUD Produtos
    public Produto adcionarProdutoBD(Produto produto) {
        ContentValues values = new ContentValues();
        values.put(ID, produto.getId());
        values.put(NOME, produto.getNome());
        values.put(DESCRICAO, produto.getDescricao());
        values.put(PRECOUNITARIO, produto.getPrecounitario());
        values.put(STOCK, produto.getStock());
        values.put(IMAGEM, produto.getImagem());

        //values.put(IVA_ID,a.getIva_id());

        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int) db.insert(TABLE_PRODUTOS, null, values);
        if (id > -1) {
            produto.setId(id);
            return produto;
        }
        return null;
    }

    public void removerAllProdutos() {
        db.delete(TABLE_PRODUTOS, null, null);
    }

    public ArrayList<Produto> getAllProdutosBD() {
        ArrayList<Produto> produtos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_PRODUTOS, new String[]{ID, NOME, DESCRICAO, PRECOUNITARIO, STOCK, IMAGEM/*,IVA_ID*/},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Produto auxProduto = new Produto(cursor.getInt(0), cursor.getString(1)
                        , cursor.getString(2), cursor.getDouble(3),
                        cursor.getInt(4), cursor.getString(5));

                produtos.add(auxProduto);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return produtos;
    }

    //endregion



    //region CRUD Servicos
    public Servico adcionarServicoBD(Servico servico) {
        ContentValues values = new ContentValues();
        values.put(ID, servico.getId());
        values.put(REFERENCIA, servico.getReferencia());
        values.put(NOME, servico.getNome());
        values.put(DESCRICAO, servico.getDescricao());
        values.put(PRECO, servico.getPreco());
        values.put(IVA_ID, servico.getIva_id());


        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int) db.insert(TABLE_SERVICOS, null, values);
        if (id > -1) {
            servico.setId(id);
            return servico;
        }
        return null;
    }

    public void removerAllServicos() {
        db.delete(TABLE_SERVICOS, null, null);
    }

    public ArrayList<Servico> getAllServicosBD() {
        ArrayList<Servico> servicos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_SERVICOS, new String[]{ID, REFERENCIA,NOME,DESCRICAO,PRECO,IVA_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Servico auxServico = new Servico(cursor.getInt(0), cursor.getString(1)
                        , cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getDouble(5), cursor.getInt(6));

                servicos.add(auxServico);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return servicos;
    }

    //endregion



}
