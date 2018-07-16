package br.edu.ifg.persist.DAO;

import android.database.Cursor;

import java.util.List;

import br.edu.ifg.model.Produto;

public interface ProdutoDAO {
    long    salvar(Produto p);
    void    criar(Produto p);
    Produto buscar(String string);
    long    alterar(String produtoAnterior, Produto novoProduto);
    long    excluir(Produto p);

    List<Produto> listarTodos();
    List<String> listar();

    //Cursor listarProdutos();

}//fim da interface
