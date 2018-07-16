package br.edu.ifg.appcadproduto;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import br.edu.ifg.model.Produto;
import br.edu.ifg.persist.DAO.FabricaDAO;
import br.edu.ifg.persist.DAO.ProdutoDAO;
import br.edu.ifg.persist.SQLiteDAO.ProdutoDaoSqlite;
import br.edu.ifg.util.MyApp;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;

public class ActivityLista extends AppCompatActivity {

    //Instancias
    ProdutoDaoSqlite produtoDaoSqlite = new ProdutoDaoSqlite();
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        produtoDAO = FabricaDAO.criarProdutoDAO();
        //Pega a referência para a ListView pelo seu ID
        ListView listView = (ListView) findViewById(R.id.lstItens);
        //Define um List que recebe um ArrayList de produtos do método listarTodos
        List<Produto> produtos = produtoDAO.listarTodos();
        //Cria um Adapter de produtos passando o layout simples para cada item
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(MyApp.getContext(), android.R.layout.simple_list_item_1,produtos);
        //Adiciona esse adapter na ListView
        listView.setAdapter(adapter);


        //Captura o id do item clicado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo;
                Toast.makeText(MyApp.getContext(),"id " + i,Toast.LENGTH_LONG).show();

            }
        });


        //Cria um menu de contexto no ListView
        /*
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo menuInfo) {
                //Adiciona os itens do menu pasaando os parâmetros
                //(Group id, ItemId, Oder, Text
                contextMenu.add(Menu.NONE, 1,Menu.NONE,"Deletar");
                contextMenu.add(Menu.NONE, 2, Menu.NONE, "Alterar");

            }
        });//Fim do contexto
        */

    }//fim do onCreate


    //Pega o item do menu de contexto
    /*
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //pega posição do item que chamou o context menu
        int position = menuInfo.position;
        //Verifica o id do menu selecionado
        switch (item.getItemId()) {
            case 1://Deletar
                //Produto produto = new Produto();
                        //produto = produtoDaoSqlite.buscar(2);
                //produtoDaoSqlite.buscar(position);
                Toast.makeText(MyApp.getContext(),"id " + position, Toast.LENGTH_LONG).show();

            case 2://Alterar

            default:
                return ActivityLista.super.onContextItemSelected(item);
        }
    }*/


}//fim da classe
