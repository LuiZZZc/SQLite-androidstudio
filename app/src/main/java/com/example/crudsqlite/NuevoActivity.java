package com.example.crudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlite.db.DbPersonas;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        // Inicializa los EditText y el botón
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Configura el listener para el botón de guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarGuardado();
            }
        });
    }

    private void confirmarGuardado() {
        // Crear un cuadro de diálogo para confirmar el guardado
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Desea confirmar los cambios?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        guardarRegistro();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void guardarRegistro() {
        String nombre = txtNombre.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String correo = txtCorreoElectronico.getText().toString();

        DbPersonas dbPersonas = new DbPersonas(this);
        long id = dbPersonas.insertarPersona(nombre, telefono, correo);

        if (id > 0) {
            Toast.makeText(this, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);  // Devuelve resultado exitoso
            finish();  // Cierra la actividad
        } else {
            Toast.makeText(this, "Error al guardar el registro", Toast.LENGTH_SHORT).show();
        }
    }
}
