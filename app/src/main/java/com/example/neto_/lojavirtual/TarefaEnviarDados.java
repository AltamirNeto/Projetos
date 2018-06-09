package com.example.neto_.lojavirtual;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.neto_.lojavirtual.conversor.ConversorProduto;
import com.example.neto_.lojavirtual.dao.DaoProduto;
import com.example.neto_.lojavirtual.modelo.Produto;

import java.util.List;

public class TarefaEnviarDados extends AsyncTask<Void, Void, String> {
    private Context c;
    private ProgressDialog dialog;

    public TarefaEnviarDados(Context c) {
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(c, "Aguarde", "Enviando dados...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        DaoProduto dao = new DaoProduto(c);
        List<Produto> produtos = dao.listaProdutos();
        dao.close();

        ConversorProduto conversor = new ConversorProduto();
        String json = conversor.converteParaJson(produtos);

        WebClient client = new WebClient();
        String response = client.post(json);
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        dialog.dismiss();
        Toast.makeText(c, response, Toast.LENGTH_SHORT).show();
    }
}
