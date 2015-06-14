package com.romelapj.usuariosmenu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.romelapj.usuariosmenu.Adaptador.UserAdapter;
import com.romelapj.usuariosmenu.R;
import com.romelapj.usuariosmenu.db.DataBaseApp;
import com.romelapj.usuariosmenu.model.Usuario;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ListUserActivity extends AppCompatActivity {
    @InjectView(R.id.lvUsuarios)
    ListView lvUsuarios;
    private Object usuariosEnLinea;
    DataBaseApp dba=new DataBaseApp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        UserAdapter userAdapter;
        if (intent.getStringExtra(InsideActivity.USUARIOS).equals("inline")) {
            userAdapter= new UserAdapter(this, getUsuariosEnLinea());
        } else {
            userAdapter= new UserAdapter(this, getUsuariosRegistrados());
        }

        lvUsuarios.setAdapter(userAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_user, menu);
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

    public ArrayList<Usuario> getUsuariosEnLinea() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("Maria Angelica", "marian", ""));
        usuarios.add(new Usuario("Diego Alejandro", "alejo", ""));
        usuarios.add(new Usuario("Andres Felipe", "pipe", ""));
        usuarios.add(new Usuario("Leidy Catalina", "cata", ""));
        usuarios.add(new Usuario("Vanesa Andrea", "vane", ""));
        usuarios.add(new Usuario("Jessica Alejandra", "jess", ""));
        usuarios.add(new Usuario("Jose Mario", "jota", ""));

        return usuarios;
    }
    private ArrayList<Usuario> getUsuariosRegistrados(){
        return dba.recoveryUser();
    }
}
