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
    DataBaseApp dba =
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
    }

    public void onRegitrarUsuario(View view){
        tvErrorRegistro.setVisibility(View.GONE);
        String usuario=etUsuario.getText().toString();
        String password=etPassword.getText().toString();
        String nombre=etNombre.getText().toString();
        user=new Usuario(nombre,usuario,password);
        if(dba.onRegister(user)){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            onError();
        }
    }

    public void onError() {
        tvErrorRegistro.setVisibility(View.VISIBLE);
        tvErrorRegistro.setText("El usuario "+user.getUsuario()+" ya se encuentra registrado");
        etUsuario.setText("");
        etPassword.setText("");
    }

}
