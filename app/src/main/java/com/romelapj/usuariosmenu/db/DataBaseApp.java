package com.romelapj.usuariosmenu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.romelapj.usuariosmenu.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romelapj on 9/06/15.
 */
public class DataBaseApp extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;

    private static final String NOMBRE_BASEDATOS = "agenda.db";

    private static final String TABLA_USUARIOS = "CREATE TABLE usuarios" +
            "(usuario TEXT PRIMARY KEY, nombre TEXT,   password TEXT)";

    public DataBaseApp(Context context){
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_USUARIOS);
    }

    public boolean onRegister(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        try{
            if (db != null) {
                ContentValues valores = new ContentValues();
                valores.put("nombre", usuario.getNombre());
                valores.put("usuario", usuario.getUsuario());
                valores.put("password", usuario.getPassword());
                db.insertOrThrow("usuarios", null, valores);
                db.close();
                return true;
            }
        }catch (SQLiteConstraintException ex){
            Log.e("prueba", "error");
            return false;
        }
        return false;
    }

    public Usuario onSearch(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM usuarios Where  usuario= '" + usuario.getUsuario() + "' AND password = '" + usuario.getPassword() + "';", null);
            if (cursor.getCount() == 1) {
                cursor.moveToFirst();
            }
            usuario.setNombre(cursor.getString(1));
            cursor.close();
            db.close();
        }catch (Exception ex){ }

        return usuario;
    }
    public ArrayList<Usuario> recoveryUser() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Usuario> lista_contactos = new ArrayList<Usuario>();
        String[] valores_recuperar = {"nombre", "usuario"};
        Cursor c = db.query("usuarios", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Usuario usuarios = new Usuario(c.getString(0), c.getString(1));
            lista_contactos.add(usuarios);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_contactos;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        onCreate(db);
    }
}