package br.edu.ifg.persist.SQLiteDAO;

import android.database.sqlite.SQLiteDatabase;
import br.edu.ifg.persist.DatabaseHelper;
import br.edu.ifg.util.MyApp;

public class GenericDaoSqlite {
    public SQLiteDatabase getWritebleDB(){
        return DatabaseHelper.getInstance(MyApp.getContext()).getWritableDatabase();
    }

    public SQLiteDatabase getReadableDB(){
        return DatabaseHelper.getInstance(MyApp.getContext()).getReadableDatabase();
    }

}//fim da classe
