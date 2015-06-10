package com.romelapj.usuariosmenu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.romelapj.usuariosmenu.view.InfoDialog;
import com.romelapj.usuariosmenu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tbMain)
    Toolbar tbMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(tbMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info) {
            Intent intent=new Intent(this,InfoDialog.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_register) {
            Intent intent=new Intent(this,InfoDialog.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_login) {
            Intent intent=new Intent(this,InfoDialog.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
