package br.edu.ifg.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.ifg.util.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper {
    //variável estática com o nome da base de dados
    private static final String BANCO_DADOS = "bdados";
    //Variável estática para constrole de versão
    private static final int VERSAO = 1;

    private static DatabaseHelper instance;

    //Método construtor
    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS,null, VERSAO);
    }

    //Método que pega a instância
    public static DatabaseHelper getInstance(Context context){

        if(instance==null)
            instance = new DatabaseHelper(context);

        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ddl =    "CREATE TABLE produtos("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT not null, " +
                "vlUnitario REAL not null, "+
                "estoque INT not null)";

        sqLiteDatabase.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


    }


}
