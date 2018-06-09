package com.example.neto_.lojavirtual;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.neto_.lojavirtual.conversor.ConversorProduto;
import com.example.neto_.lojavirtual.dao.DaoProduto;
import com.example.neto_.lojavirtual.modelo.Produto;

import java.util.List;

public class Lista_Produtos_Activity extends AppCompatActivity {

    private ListView listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        listaProdutos = (ListView) findViewById(R.id.lista_produtos);


        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {

                Produto p = (Produto) listaProdutos.getItemAtPosition(position);
                Intent intentDetalhes = new Intent(Lista_Produtos_Activity.this, ProdutoDetalhesActivity.class);
                intentDetalhes.putExtra("produto", p);
                startActivity(intentDetalhes);

            }
        });



        Button novoProduto = (Button) findViewById(R.id.novo_produto);  //botão para adicionar um novo produto
        novoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNovoProduto = new Intent(Lista_Produtos_Activity.this, FormularioActivity.class);
                startActivity(intentNovoProduto);
                
            }
        });


        registerForContextMenu(listaProdutos);  //para adicionar o evento do click longo
    }

    private void carregaLista() {   //carregar dados do banco
        DaoProduto dao = new DaoProduto(this);
        List<Produto> p = dao.listaProdutos();
        dao.close();

        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, p);
        listaProdutos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    //criar o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_produtos, menu);

        return true;
    }

    //usar o icone no menu para enviar dados em forma de Json
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_envia_dados:
                new TarefaEnviarDados(this).execute();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //Evento de click longo, deletar
    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto p = (Produto) listaProdutos.getItemAtPosition(info.position);

                Intent intentNovoProduto = new Intent(Lista_Produtos_Activity.this, FormularioActivity.class);
                intentNovoProduto.putExtra("produto", p);
                startActivity(intentNovoProduto);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto p = (Produto) listaProdutos.getItemAtPosition(info.position);

                DaoProduto dao = new DaoProduto(Lista_Produtos_Activity.this);
                dao.deleta(p);
                dao.close();
                carregaLista();
                return false;
            }
        });
    }
}
