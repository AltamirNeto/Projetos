package com.example.neto_.lojavirtual.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.neto_.lojavirtual.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class DaoProduto extends SQLiteOpenHelper {
    public DaoProduto(Context c) {
        super(c, "LojaVirtual", null, 1);
    }

    //Criando a tabela no banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, autor TEXT, descricao TEXT, preco TEXT);";
        db.execSQL(sql);
    }

    // atualizando a tabela no banco de dados e excluindo caso ja tenha uma.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Produtos";
        db.execSQL(sql);
        onCreate(db);
    }

    //metodo Adicionar
    public void adiciona(Produto p) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesProduto(p);

        db.insert("Produtos", null, dados);
    }

    //metodo para criar um ContentValues
    @NonNull
    private ContentValues getContentValuesProduto(Produto p) {
        ContentValues dados = new ContentValues();
        dados.put("nome", p.getNome());
        dados.put("autor", p.getAutor());
        dados.put("descricao", p.getDescricao());
        dados.put("preco", p.getPreco());
        return dados;
    }


    //metodo Listar
    public List<Produto> listaProdutos() {
        String sql = "SELECT * FROM Produtos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();
        while(c.moveToNext()){
            Produto p = new Produto();
            p.setId(c.getLong(c.getColumnIndex("id")));
            p.setNome(c.getString(c.getColumnIndex("nome")));
            p.setAutor(c.getString(c.getColumnIndex("autor")));
            p.setDescricao(c.getString(c.getColumnIndex("descricao")));
            p.setPreco(c.getString(c.getColumnIndex("preco")));

            produtos.add(p);
        }
        c.close();
        return produtos;
    }

    //metodo deletar
    public void deleta(Produto p) {
        SQLiteDatabase db = getWritableDatabase();

        String params = String.valueOf(p.getId());
        db.delete("Produtos", "id = ?", new String[]{params});
    }

    //metodo alterar
    public void altera(Produto p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(p);

        String params = String.valueOf(p.getId());
        db.update("Produtos", dados, "id = ?", new String[]{params});
    }
}
