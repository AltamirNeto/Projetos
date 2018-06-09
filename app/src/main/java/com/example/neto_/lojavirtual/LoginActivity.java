package com.example.neto_.lojavirtual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button bt_login, bt_registrar;
    DBUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBUsuario(this);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_registrar = (Button) findViewById(R.id.bt_reg_registrar);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (username.equals("")){
                    Toast.makeText(LoginActivity.this, "O usuario deve ser preenchido", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")){
                    Toast.makeText(LoginActivity.this, "A senha deve ser preenchida", Toast.LENGTH_SHORT).show();
                } else {
                    String res = db.validarUsuario(username, password);

                    if (res.equals("OK")){
                        Toast.makeText(LoginActivity.this, "Logado", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent (LoginActivity.this, Lista_Produtos_Activity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login errado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, RegistrarActivity.class);
                startActivity(i);
            }
        });
    }
}
