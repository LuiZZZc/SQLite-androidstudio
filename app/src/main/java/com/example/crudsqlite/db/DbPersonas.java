package com.example.crudsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.crudsqlite.entidades.Personas;

import java.util.ArrayList;

public class DbPersonas extends DbHelper {

    Context context;

    public DbPersonas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    // Método para insertar una persona
    public long insertarPersona(String nombre, String telefono, String correo_electronico) {
        long id = 0;

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

            id = db.insert(TABLE_PERSONAS, null, values);
        } catch (Exception ex) {
            ex.printStackTrace(); // Cambié esto para que imprima la excepción
        }

        return id;
    }

    // Método para mostrar todas las personas
    public ArrayList<Personas> mostrarPersonas() {
        ArrayList<Personas> listaPersonas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // Cambié a getReadableDatabase

        Cursor cursorPersonas = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS, null);

        if (cursorPersonas.moveToFirst()) {
            do {
                Personas persona = new Personas();
                persona.setId(cursorPersonas.getInt(0));
                persona.setNombre(cursorPersonas.getString(1));
                persona.setTelefono(cursorPersonas.getString(2));
                persona.setCorreo_electronico(cursorPersonas.getString(3));
                listaPersonas.add(persona);
            } while (cursorPersonas.moveToNext());
        }
        cursorPersonas.close();
        return listaPersonas;
    }

    // Método para ver una persona específica
    public Personas verPersona(int id) {
        Personas persona = null;
        SQLiteDatabase db = this.getReadableDatabase(); // Cambié a getReadableDatabase

        Cursor cursorPersonas = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS + " WHERE id = ? LIMIT 1", new String[]{String.valueOf(id)});

        if (cursorPersonas.moveToFirst()) {
            persona = new Personas();
            persona.setId(cursorPersonas.getInt(0));
            persona.setNombre(cursorPersonas.getString(1));
            persona.setTelefono(cursorPersonas.getString(2));
            persona.setCorreo_electronico(cursorPersonas.getString(3));
        }
        cursorPersonas.close();
        return persona;
    }

    // Método para editar una persona
    public boolean editarPersona(int id, String nombre, String telefono, String correo_electronico) {
        boolean correcto = false;

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            correcto = db.update(TABLE_PERSONAS, values, "id = ?", new String[]{String.valueOf(id)}) > 0;
        } catch (Exception ex) {
            ex.printStackTrace(); // Cambié esto para que imprima la excepción
        } finally {
            db.close();
        }

        return correcto;
    }

    // Método para eliminar una persona
    public boolean eliminarPersona(int id) {
        boolean correcto = false;

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            correcto = db.delete(TABLE_PERSONAS, "id = ?", new String[]{String.valueOf(id)}) > 0;
        } catch (Exception ex) {
            ex.printStackTrace(); // Cambié esto para que imprima la excepción
        } finally {
            db.close();
        }

        return correcto;
    }
}
