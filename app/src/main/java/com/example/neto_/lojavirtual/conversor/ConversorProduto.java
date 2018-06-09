package com.example.neto_.lojavirtual.conversor;

import com.example.neto_.lojavirtual.modelo.Produto;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

//classe para converter para Json

public class ConversorProduto {
    public String converteParaJson(List<Produto> produtos) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("list").array().object().key("p").array();
            for(Produto p : produtos){
                js.object();
                js.key("nome").value(p.getNome());
                js.key("autor").value(p.getAutor());
                js.key("descricao").value(p.getDescricao());
                js.key("preco").value(p.getPreco());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
