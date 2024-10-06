package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.crudsqlite.adaptadores.PersonaAdapter;
import com.example.crudsqlite.db.DbPersonas;
import com.example.crudsqlite.entidades.Personas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listapersonas;
    ArrayList<Personas> listaArrayPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el RecyclerView
        listapersonas = findViewById(R.id.listaContactos);
        listapersonas.setLayoutManager(new LinearLayoutManager(this));

        // Cargar la lista de personas
        cargarLista();
    }

    private void cargarLista() {
        // Inicializa la base de datos y carga la lista de personas
        DbPersonas dbPersonas = new DbPersonas(this);
        listaArrayPersonas = dbPersonas.mostrarPersonas();

        // Verifica si la lista está vacía
        if (listaArrayPersonas.isEmpty()) {
            Log.d("MainActivity", "La lista de personas está vacía.");
        } else {
            Log.d("MainActivity", "Se encontraron " + listaArrayPersonas.size() + " personas.");
        }

        PersonaAdapter adapter = new PersonaAdapter(listaArrayPersonas);
        listapersonas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.registro_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro() {
        // Inicia la actividad de nuevo registro
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivityForResult(intent, 1);  // Código de solicitud 1
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Recargar la lista después de guardar
            cargarLista();
        }
    }
}
