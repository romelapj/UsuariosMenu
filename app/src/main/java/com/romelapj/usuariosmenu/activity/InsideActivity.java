package com.romelapj.usuariosmenu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.romelapj.usuariosmenu.Adaptador.UserAdapter;
import com.romelapj.usuariosmenu.R;
import com.romelapj.usuariosmenu.model.Usuario;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InsideActivity extends AppCompatActivity {
    public final static String USUARIOS="usuarios";
    @InjectView(R.id.tbInside)
    Toolbar tbInside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);
        ButterKnife.inject(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(tbInside);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_users) {
            Intent intent=new Intent(InsideActivity.this,ListUserActivity.class);
            intent.putExtra(USUARIOS,"register");
            startActivity(intent);
            return true;
        }else if (id == R.id.action_inline){
            Intent intent=new Intent(InsideActivity.this,ListUserActivity.class);
            intent.putExtra(USUARIOS,"inline");
            startActivity(intent);
            return true;
        }else if(id==R.id.action_logout){
            SharedPreferences prefs =
                    getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", "");
            editor.putString("password", "");
            editor.commit();
            Intent intent=new Intent(InsideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
