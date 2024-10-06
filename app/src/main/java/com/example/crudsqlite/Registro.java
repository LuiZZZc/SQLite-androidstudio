package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlite.db.DbUsuario;
import com.example.crudsqlite.entidades.Usuario;

public class Registro extends AppCompatActivity implements View.OnClickListener {
EditText us,pas, con;
Button reg, can;
DbUsuario db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        us=(EditText)findViewById(R.id.txtUs);
        pas=(EditText)findViewById(R.id.txtPas);
        con=(EditText)findViewById(R.id.txtconfirm);
        reg=(Button)findViewById(R.id.btnRegister);
        can=(Button)findViewById(R.id.btnLogin);

        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        db= new DbUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                // Obtener las contraseñas de los campos
                String password = pas.getText().toString();
                String confirmPassword = con.getText().toString();

                // Crear el usuario
                Usuario user = new Usuario();
                user.setUsuario(us.getText().toString());
                user.setPassword(password);

                // Verificar que ningún campo esté vacío
                if (user.getUsuario().isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "ERROR: Campos vacíos", Toast.LENGTH_LONG).show();
                    return;  // Detener la ejecución
                }

                // Verificar si las contraseñas coinciden
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(this, "ERROR: Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;  // Detener la ejecución si no coinciden
                }

                // Si no hay errores, proceder con el registro
                if (db.insertUsuario(user)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    // Navegar al Login solo si el registro es exitoso
                    Intent i2 = new Intent(Registro.this, Login.class);
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btnLogin:
                // Navegar al Login si el botón de login es presionado
                Intent i = new Intent(Registro.this, Login.class);
                startActivity(i);
                finish();
                break;
        }
    }

}