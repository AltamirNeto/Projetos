package com.example.neto_.lojavirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neto_.lojavirtual.dao.DaoProduto;
import com.example.neto_.lojavirtual.modelo.Produto;

public class FormularioActivity extends AppCompatActivity {

    private AuxiliarFormulario aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        aux = new AuxiliarFormulario(this);

        Intent intent = getIntent();
        Produto p = (Produto) intent.getSerializableExtra("produto");
        if(p != null){
            aux.preencheFormulario(p);  //caso for atualizar o produto, executará esse metodo
        }
    }

    //criar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //usar o icone no menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_adiciona:

                Produto p = aux.pegaProduto();
                DaoProduto dao = new DaoProduto(this);
                if(p.getId() != null){      //caso o produto exista, alterará, senão, adicionará um novo produto.
                    dao.altera(p);      //alterar produto
                } else {
                    dao.adiciona(p);    //adicionar o produto
                }

                dao.close();
                Toast.makeText(FormularioActivity.this,
                        "Produto " + p.getNome() + " salvo com Sucesso!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
