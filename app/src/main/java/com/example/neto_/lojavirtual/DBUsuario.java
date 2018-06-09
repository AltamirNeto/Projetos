package com.example.neto_.lojavirtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUsuario extends SQLiteOpenHelper {

    public DBUsuario(Context c) {
        super( c , "Login.Registro", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuario (username TEXT PRIMARY KEY, password TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario;");
        onCreate(db);
    }

    public long CriarUsuario(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long result = db.insert("Usuario",null,cv);
        return result;
    }

    public String validarUsuario(String username, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuario WHERE username=? AND password=?", new String[]{username, password});
        if(c.getCount() > 0){
            return "OK";
        }
        return "ERRO";
    }
}
