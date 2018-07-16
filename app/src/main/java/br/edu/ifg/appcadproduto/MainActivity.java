package br.edu.ifg.appcadproduto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifg.persist.DAO.FabricaDAO;
import br.edu.ifg.persist.DAO.ProdutoDAO;

public class MainActivity extends AppCompatActivity {

    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botao cadastrar
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityCadastro.class);
                intent.putExtra("op","cadastrar");
                startActivity(intent);
            }
        });

        //Botao listar
        Button btnLitar = findViewById(R.id.btnListar);
        btnLitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, ActivityLista.class);
                //startActivity(intent);
                lista();
            }
        });

    }//fim do onCreate
    public void lista() {
        produtoDAO = FabricaDAO.criarProdutoDAO();
        final CharSequence[] items = produtoDAO.listar().toArray(new CharSequence[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Produtos Cadastrados");

        builder.setItems(items, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int item) {
                Intent intent = new Intent(MainActivity.this,ActivityCadastro.class);
                intent.putExtra("op","excluir");
                intent.putExtra("nome",items[item]);
                startActivity(intent);
                //onPause();
            }
        });
        builder.create().show();
    }
}//fim da classe
