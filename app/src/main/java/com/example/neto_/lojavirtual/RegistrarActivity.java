package com.example.neto_.lojavirtual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {
    EditText et_reg_username, et_reg_password, et_reg_password2;
    Button bt_reg_registrar;
    DBUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        db = new DBUsuario(this);

        et_reg_username = (EditText) findViewById(R.id.et_reg_username);
        et_reg_password = (EditText) findViewById(R.id.et_reg_password);
        et_reg_password2 = (EditText) findViewById(R.id.et_reg_password2);

        bt_reg_registrar = (Button) findViewById(R.id.bt_reg_registrar);

        bt_reg_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_reg_username.getText().toString();
                String p1 = et_reg_password.getText().toString();
                String p2 = et_reg_password2.getText().toString();

                if(username.equals("")){
                    Toast.makeText(RegistrarActivity.this, "O usuario deve ser preenchido", Toast.LENGTH_SHORT).show();
                } else if (p1.equals("") || p2.equals("")){
                    Toast.makeText(RegistrarActivity.this, "A senha deve ser preenchida", Toast.LENGTH_SHORT).show();
                } else if (!p1.equals(p2)){
                    Toast.makeText(RegistrarActivity.this, "Ambas as senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                } else {
                    long res = db.CriarUsuario(username, p1);
                    if (res > 0 ){
                        Toast.makeText(RegistrarActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Registro invalido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
