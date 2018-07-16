package br.edu.ifg.persist.SQLiteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifg.model.Produto;
import br.edu.ifg.persist.DAO.ProdutoDAO;
import br.edu.ifg.persist.DatabaseHelper;
import br.edu.ifg.util.MyApp;


public class ProdutoDaoSqlite extends GenericDaoSqlite implements ProdutoDAO {
    @Override
    public long salvar(Produto p) {
        SQLiteDatabase db = getWritebleDB();
        long id;
        List<String> myArray = new ArrayList<>();
        myArray = this.listar();

        if(myArray.contains(p.getNome())){
            id =-1;
        }else {

            ContentValues values = new ContentValues();
            values.put("nome", p.getNome());
            values.put("vlUnitario", p.getVlUnitario());
            values.put("estoque", p.getEstoque());
            id = db.insert("produtos", null, values);
        }
        return id;
    }//fim salvar

    @Override
    public void criar(Produto p) {

    }

    @Override
    public Produto buscar(String string) {
        DatabaseHelper helper = DatabaseHelper.getInstance(MyApp.getContext());
        SQLiteDatabase db = getReadableDB();
        Cursor cursor = db.rawQuery("SELECT * from produtos where nome = '"+ string + "'", null);
        Produto produto = new Produto();

        if (cursor != null){
            if(cursor.moveToFirst()){
                do {
                    //produto.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                    produto.setNome(cursor.getString(1));
                    produto.setVlUnitario(Float.valueOf(cursor.getString(2)));
                    produto.setEstoque(Integer.parseInt(cursor.getString(3)));
                }while (cursor.moveToNext());
            }
        }
        return produto;
    }//fim buscar

    @Override
    public long alterar(String produtoAnterior, Produto novoProduto) {
        SQLiteDatabase db = getWritebleDB();
        ContentValues values = new ContentValues();

        values.put("nome", novoProduto.getNome());
        values.put("vlUnitario", novoProduto.getVlUnitario());
        values.put("estoque", novoProduto.getEstoque());

        String where = "nome = ?";
        String[] whereArgs = {produtoAnterior};
        long id2 = db.update("produtos",values,where,whereArgs);
        return id2;
    }//fim alterar

    @Override
    public long excluir(Produto p) {
        //DatabaseHelper helper = DatabaseHelper.getInstance(MyApp.getContext());
        SQLiteDatabase db =getWritebleDB();
        //Cursor cursor = db.rawQuery("DELETE * from produtos where _id="+ id, null);
        String where = "nome = '" + p.getNome() + "'";
        long id3 = db.delete("produtos" ,where, null);
        return id3;
    }//fim excluir

    @Override
    public List<Produto> listarTodos() {

        //Instancia o helper obtendo o contexto
        DatabaseHelper helper = DatabaseHelper.getInstance(MyApp.getContext());
        SQLiteDatabase db = getReadableDB();
        //Cursor recebe o resultado da consulta via SQL
        Cursor cursor = db.rawQuery("SELECT * from produtos", null);
        //move o cursor para a primeira posição
        cursor.moveToFirst();

        List<Produto> produtos = new ArrayList<Produto>();

        //Enquanto cursor mover para o proximo item
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            //Adiciona cada valor da coluna ao atribudo do objeto produto
            produto.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            produto.setVlUnitario(cursor.getLong(cursor.getColumnIndex("vlUnitario")));
            produto.setEstoque(cursor.getInt(cursor.getColumnIndex("estoque")));
            //Adiciona o objeto ao List
            produtos.add(produto);
        }
        //fecha a consulta
        cursor.close();
        //retorna o List de objetos
        return produtos;
    }

    public List<String> listar() {
        List<String> myArray = new ArrayList<>();
        SQLiteDatabase db = getReadableDB();
        Cursor cursor = db.rawQuery("Select nome From produtos",null);
        if(cursor!= null){
            if(cursor.moveToFirst()){
                do {
                    String descricao = cursor.getString(0);
                    myArray.add(descricao);
                }while(cursor.moveToNext());
            }
        }
        return myArray;
    }//fim listar

}//fim da classe
