package br.edu.ifg.persist.DAO;

import br.edu.ifg.persist.SQLiteDAO.ProdutoDaoSqlite;

public class FabricaDAO {
    public static ProdutoDAO criarProdutoDAO(){
        return new ProdutoDaoSqlite();
    }
}//fim da classe
