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

    private static final String TABLE_PRODUTOS = "produtos", TABLE_SERVICOS = "servicos",
            TABLE_DIAGNOSTICOS = "diagnosticos", TABLE_IVAS = "ivas", TABLE_LINHASFATURAS = "linhasfaturas",
            TABLE_FATURAS = "faturas";
    private final SQLiteDatabase db;

    private static final String ID = "id",
            VALORTOTAL = "valortotal", IVATOTAL = "ivatotal", SUBTOTAL = "subtotal", DATA = "data",
            QUANTIDADE = "quantidade", VALORUNITARIO = "valorunitario", VALORIVA = "valoriva", EMVIGOR = "emvigor",
            PERCENTAGEM = "percentagem", FATURA_ID = "fatura_id", PRODUTO_ID = "produto_id", SERVICO_ID = "servico_id",
            NOME = "nome", DESCRICAO = "descricao", PRECOUNITARIO = "precounitario", STOCK = "stock",
            REFERENCIA = "referencia", PRECO = "preco", IVASPERCENTAGEM = "ivaspercentagem", HORA = "hora", ESTADO="estado",

            IMAGEM = "imagem",

            CONSULTA_ID = "consulta_id", PROFILE_ID = "profile_id";

    private static final String TABLE_MARCACOES ="consultas";



    public BDHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCreateTable="CREATE TABLE "+TABLE_MARCACOES+
                "(" + ID +" INTEGER PRIMARY KEY , "+
                DESCRICAO + " TEXT, "+
                DATA + " TEXT NOT NULL, "+
                HORA + " TEXT NOT NULL, "+
                ESTADO + " TEXT NOT NULL, "+
                PRODUTO_ID + " FOREIGNKEY NOT NULL," +
                SERVICO_ID  + " FOREIGNKEY NOT NULL);";

        sqLiteDatabase.execSQL(sqlCreateTable);

        String createTableProdutos = "CREATE TABLE " + TABLE_PRODUTOS + "(" + ID + " INTEGER PRIMARY KEY," +
                NOME + " TEXT NOT NULL," +
                DESCRICAO + " TEXT NOT NULL," +
                PRECOUNITARIO + " DOUBLE NOT NULL," +
                STOCK + " INTEGER NOT NULL," +
                IMAGEM + " TEXT  NOT NULL);";


        String createTableServicos = "CREATE TABLE " + TABLE_SERVICOS + "(" + ID + " INTEGER PRIMARY KEY," +
                NOME + " TEXT NOT NULL," +
                DESCRICAO + " TEXT NOT NULL," +
                IVASPERCENTAGEM + " TEXT NOT NULL," +
                PRECO + " DOUBLE NOT NULL," +
                IMAGEM + " TEXT  NOT NULL);";
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
    */

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




        sqLiteDatabase.execSQL(createTableProdutos);

        sqLiteDatabase.execSQL(createTableServicos);
        /*
        sqLiteDatabase.execSQL(createTableIVAS);
        sqLiteDatabase.execSQL(createTableDiagnosticos);
         */
        sqLiteDatabase.execSQL(createTableLinhasFaturas);
        sqLiteDatabase.execSQL(createTableFaturas);




    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sqlDeleteTableProduto = "DROP TABLE IF EXISTS " + TABLE_PRODUTOS;
        String sqlDeleteTableServicos = "DROP TABLE IF EXISTS " + TABLE_SERVICOS;
        String sqlDeleteTableIVAS = "DROP TABLE IF EXISTS " + TABLE_IVAS;
        String sqlDeleteTableDiagnosticos = "DROP TABLE IF EXISTS " + TABLE_DIAGNOSTICOS;
        String sqlDeleteTableLinhasFaturas = "DROP TABLE IF EXISTS " + TABLE_LINHASFATURAS;
        String sqlDeleteTableFaturas = "DROP TABLE IF EXISTS " + TABLE_FATURAS;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_MARCACOES);


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
    public Servico adicionarServicoBD(Servico servico) {
        ContentValues values = new ContentValues();
        values.put(ID, servico.getId());
        values.put(NOME, servico.getNome());
        values.put(DESCRICAO, servico.getDescricao());
        values.put(IVASPERCENTAGEM, servico.getIvaspercentagem());
        values.put(PRECO, servico.getPreco());
        values.put(IMAGEM, servico.getImagem());


        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int) db.insert(TABLE_SERVICOS, null, values);
        if (id > -1) {
            servico.setId(id);
            return servico;
        }
        return null;
    }

    public void removerAllServicos() {db.delete(TABLE_SERVICOS, null, null);}

    public ArrayList<Servico> getAllServicosBD() {
        ArrayList<Servico> servicos = new ArrayList<>();
        Cursor cursor = db.query(TABLE_SERVICOS, new String[]{ID,NOME,DESCRICAO,IVASPERCENTAGEM,PRECO,IMAGEM},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Servico auxServico = new Servico(cursor.getInt(0), cursor.getString(1)
                        , cursor.getString(2), cursor.getString(3), cursor.getDouble(4),cursor.getString(5));

                servicos.add(auxServico);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return servicos;
    }

    //endregion


    //region CRUD Faturas
    public Fatura adcionarFaturaBD(Fatura fatura) {
        ContentValues values = new ContentValues();
        values.put(ID, fatura.getId());
        values.put(PROFILE_ID, fatura.getProfile_id());
        values.put(DATA, fatura.getData());
        values.put(VALORTOTAL, fatura.getValortotal());
        values.put(IVATOTAL, fatura.getIvatotal());
        values.put(SUBTOTAL, fatura.getSubtotal());
        values.put(ESTADO, fatura.getEstado());

        //values.put(IVA_ID,a.getIva_id());

        // db.insert retorna -1 em caso de erro ou o id que foi criado
        int id = (int) db.insert(TABLE_FATURAS, null, values);
        if (id > -1) {
            fatura.setId(id);
            return fatura;
        }
        return null;
    }

    public void removerAllFaturas() {
        db.delete(TABLE_FATURAS, null, null);
    }

    public ArrayList<Fatura> getAllFaturasBD() {
        ArrayList<Fatura> faturas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_FATURAS, new String[]{ID, DATA, VALORTOTAL, IVATOTAL, SUBTOTAL,ESTADO},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Fatura auxFatura = new Fatura(cursor.getInt(0), cursor.getInt(1)
                        , cursor.getString(2), cursor.getDouble(3),
                        cursor.getDouble(4),cursor.getDouble(5), cursor.getString(6));

                faturas.add(auxFatura);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return faturas;
    }

    //endregion

    //Marcacoes

    public boolean editarMarcacaoBD(Consulta marcacao){

        ContentValues values = new ContentValues();
        values.put(DESCRICAO,marcacao.getDescricao());
        values.put(DATA,marcacao.getData());
        values.put(HORA,marcacao.getHora());
        values.put(ESTADO,marcacao.getEstado());
        values.put(SERVICO_ID,marcacao.getNomeservico());
        //o metodo insert devolve o id como sendo um long
        int nLinhasUpdate = this.db.update(TABLE_MARCACOES,values,ID +"=?",new String[]{marcacao.getId()+""});

        return nLinhasUpdate>0; // devolve o valor loginco da condição
    }

    public boolean removerMarcacaoBD(int idMarcacao) {
        int nLinhasDel = this.db.delete(TABLE_MARCACOES,ID +"=?",new String[]{idMarcacao+""});
        return nLinhasDel>0; // devolve o valor loginco da condição
    }

}
