package com.romelapj.usuariosmenu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.romelapj.usuariosmenu.R;
import com.romelapj.usuariosmenu.db.DataBaseApp;
import com.romelapj.usuariosmenu.model.Usuario;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    DataBaseApp dba =
            new DataBaseApp(this);
    private SQLiteDatabase db ;
    public Usuario user;



    @InjectView(R.id.etUsuarioAccess)
    EditText etUsuario;
    @InjectView(R.id.etPasswordAccess)
    EditText etPassword;
    @InjectView(R.id.tvErrorAccess)
    TextView tvErrorAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);


    }

    public void onLoginUsuario(View view){
        String usuario=etUsuario.getText().toString();
        String password=etPassword.getText().toString();
        user=new Usuario("",usuario,password);
        user=dba.onSearch(user);
        if (user.getNombre().equals("")){
            onError();
        }else {
            onComplete();
        }
    }





    public void onComplete() {
        SharedPreferences prefs =
                getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);
        tvErrorAccess.setVisibility(View.GONE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", user.getUsuario());
        editor.putString("password", user.getPassword());
        editor.putString("nombre", user.getPassword());
        editor.commit();
        Intent intent=new Intent(LoginActivity.this, InsideActivity.class);
        startActivity(intent);
    }


    public void onError() {
        tvErrorAccess.setVisibility(View.VISIBLE);
        tvErrorAccess.setText("El usuario o contraseña no se encuentra bien");
        etUsuario.setText("");
        etPassword.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
