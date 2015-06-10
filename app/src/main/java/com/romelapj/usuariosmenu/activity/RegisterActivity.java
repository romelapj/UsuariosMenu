package com.romelapj.usuariosmenu.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.romelapj.usuariosmenu.R;
import com.romelapj.usuariosmenu.db.DataBaseApp;
import com.romelapj.usuariosmenu.model.Usuario;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity {
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    DataBaseApp actdbh =
            new DataBaseApp(this);
    private SQLiteDatabase db ;


    @InjectView(R.id.etUsuario)
    EditText etUsuario;
    @InjectView(R.id.etPassword)
    EditText etPassword;
    @InjectView(R.id.etNombre)
    EditText etNombre;
    @InjectView(R.id.tvErrorRegistro)
    TextView tvErrorRegistro;




    public Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        Bundle extras = getIntent().getExtras();

    }

    public void onRegitrarUsuario(View view){
        tvErrorRegistro.setVisibility(View.GONE);
        String usuario=etUsuario.getText().toString();
        String password=etPassword.getText().toString();
        String nombre=etNombre.getText().toString();
        user=new Usuario(nombre,usuario,password);
        registrarUsuario(user.getNombre(),user.getUsuario(), user.getPassword());

    }




    public void registrarUsuario(String nombre,String usuario, String password) {
        try {
            db = actdbh.getWritableDatabase();
            if (db != null) {
                ContentValues valores = new ContentValues();
                valores.put("nombre", nombre);
                valores.put("usuario", usuario);
                valores.put("password", password);
                db.insertOrThrow("usuarios", null, valores);
                db.close();
                onComplete();
            }
        }catch (SQLiteConstraintException ex){
            Log.e("prueba", "error");
            onError();
        }
    }


    public void onComplete() {
        SharedPreferences prefs =
                getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", user.getUsuario());
        editor.putString("password", user.getPassword());
        editor.putString("nombre", user.getNombre());
        editor.commit();

        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


    public void onError() {
        tvErrorRegistro.setVisibility(View.VISIBLE);
        tvErrorRegistro.setText("El usuario "+user.getUsuario()+" ya se encuentra registrado");
        etUsuario.setText("");
        etPassword.setText("");
    }

}
