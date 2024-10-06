package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlite.db.DbUsuario;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button btnEntrar, btnRegistrar;
    DbUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.txtUser);
        pass=(EditText)findViewById(R.id.txtContraseña);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        db=new DbUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if (u.equals("")&&p.equals("")){
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                }else if(db.login(u,p)==1){
                    Toast.makeText(this, "correcto", Toast.LENGTH_LONG).show();
                    Intent in=new Intent(Login.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnRegistrar:
                Intent i=new Intent(Login.this, Registro.class);
                startActivity(i);
                break;
        }

    }
}