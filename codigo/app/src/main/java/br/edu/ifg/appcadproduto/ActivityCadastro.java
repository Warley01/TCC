package br.edu.ifg.appcadproduto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifg.model.Produto;
import br.edu.ifg.persist.DAO.FabricaDAO;
import br.edu.ifg.persist.DAO.ProdutoDAO;
import br.edu.ifg.persist.DatabaseHelper;
import br.edu.ifg.persist.SQLiteDAO.ProdutoDaoSqlite;

public class ActivityCadastro extends Activity {

    //Instâncias
    //private DatabaseHelper db;
    private ProdutoDAO produtoDAO;
    String op;
    String nomeProduto;

    //instancia um objeto produto
    Produto produto = new Produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //textViews
        final EditText edtNome =findViewById(R.id.edtNome);
        final EditText edtVlUniatio = findViewById(R.id.edtVlUnitario);
        final EditText edtEstoque = findViewById(R.id.edtEstoque);

        Intent intent = getIntent();
        op = intent.getStringExtra("op");
        nomeProduto = intent.getStringExtra("nome");


        //Botao gravar
        final Button btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //textViews
                EditText edtNome =findViewById(R.id.edtNome);
                EditText edtVlUniatio = findViewById(R.id.edtVlUnitario);
                EditText edtEstoque = findViewById(R.id.edtEstoque);
                produtoDAO = FabricaDAO.criarProdutoDAO();

                //pega os valores dos campos
                if(edtNome.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Você deve digitar um nome para o produto.", Toast.LENGTH_LONG).show();
                }else{
                    produto.setNome(edtNome.getText().toString());
                    if(edtVlUniatio.getText().length()==0){
                        produto.setVlUnitario(0.f);
                    }else{
                        produto.setVlUnitario(Float.valueOf(edtVlUniatio.getText().toString()));
                    }
                    if(edtEstoque.getText().length()==0){
                        produto.setEstoque(0);
                    }else {
                        produto.setEstoque(Integer.parseInt(edtEstoque.getText().toString()));
                    }

                    //variável de retorno
                    long id;

                    if(op=="alterar"){
                        //altera o produto
                        id = produtoDAO.alterar(nomeProduto, produto);
                    }else{
                        //salva o produto
                        id = produtoDAO.salvar(produto);
                    }


                    //Exibe uma notificação na  tela
                    if(id!=-1){
                        Toast.makeText(getApplicationContext(),"Produto " + id + " salvo com sucesso",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ActivityCadastro.this, MainActivity.class);
                        startActivity(intent);
                        //finaliza a activity
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Erro ao gravar produto",Toast.LENGTH_LONG).show();
                    }
                }//fim do if

            }
        });//fim do onClickListener

        //Botao excluir
        final Button btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Produto produto = new Produto();
                EditText edtNome =findViewById(R.id.edtNome);
                produto.setNome(edtNome.getText().toString());

                long id = produtoDAO.excluir(produto);
                if(id!=-1){
                    Toast.makeText(getApplicationContext(), "Produto excluído com sucesso", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao excluir o produto", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(ActivityCadastro.this, MainActivity.class);
                startActivity(intent);
                //finaliza a activity
                finish();
            }
        });//fim btnEcluir

        //Botao alterar
        final Button btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //habilita os campos
                edtNome.setEnabled(true);
                edtVlUniatio.setEnabled(true);
                edtEstoque.setEnabled(true);
                btnGravar.setEnabled(true);
                btnAlterar.setEnabled(false);
                btnExcluir.setEnabled(false);
                op = "alterar";

            }
        });//fim alterar

        //controla os botoes como habilitado/desabilitado
        switch (op){
            case "cadastrar":
                btnExcluir.setEnabled(false);
                btnAlterar.setEnabled(false);
                break;
            case "excluir":
                produtoDAO = FabricaDAO.criarProdutoDAO();
                produto = produtoDAO.buscar(nomeProduto);
                edtNome.setText(produto.getNome());
                edtVlUniatio.setText(String.valueOf(produto.getVlUnitario()));
                edtEstoque.setText(String.valueOf(produto.getEstoque()));
                btnExcluir.setEnabled(true);
                btnGravar.setEnabled(false);
                edtNome.setEnabled(false);
                edtVlUniatio.setEnabled(false);
                edtEstoque.setEnabled(false);

                break;
        }

    }//fim don onCreate


}//fim da classe
