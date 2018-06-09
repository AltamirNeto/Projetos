package com.example.neto_.lojavirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.neto_.lojavirtual.modelo.Produto;

public class ProdutoDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detalhes);

        Intent intent = getIntent();
        Produto p = (Produto) intent.getSerializableExtra("produto");

        TextView nome = findViewById(R.id.detalhes_produto_nome);
        TextView autor = findViewById(R.id.detalhes_produto_autor);
        TextView descricao = findViewById(R.id.detalhes_produto_descricao);
        TextView preco = findViewById(R.id.detalhes_produto_preco);

        nome.setText(p.getNome());
        autor.setText(p.getAutor());
        descricao.setText(p.getDescricao());
        preco.setText(p.getPreco());

    }
}
