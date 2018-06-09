package com.example.neto_.lojavirtual;

import android.widget.EditText;

import com.example.neto_.lojavirtual.modelo.Produto;

//classe para auxiliar o formulario de adicionar produto
public class AuxiliarFormulario {

    private final EditText campoNome;
    private final EditText campoAutor;
    private final EditText campoDescricao;
    private final EditText campoPreco;

    private Produto p;


    public AuxiliarFormulario(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoAutor = (EditText) activity.findViewById(R.id.formulario_autor);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_descricao);
        campoPreco = (EditText) activity.findViewById(R.id.formulario_preco);
        p = new Produto();
    }

    //pegar os dados dos produtos dos campos
    public Produto pegaProduto() {
        p.setNome(campoNome.getText().toString());
        p.setAutor(campoAutor.getText().toString());
        p.setDescricao(campoDescricao.getText().toString());
        p.setPreco(campoPreco.getText().toString());

        return p;
    }

    //buscar no banco os dados dos produto para edição
    public void preencheFormulario(Produto p) {
        campoNome.setText(p.getNome());
        campoAutor.setText(p.getAutor());
        campoDescricao.setText(p.getDescricao());
        campoPreco.setText(p.getPreco());
        this.p = p;
    }
}
