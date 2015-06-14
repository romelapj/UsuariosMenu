package com.romelapj.usuariosmenu.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.romelapj.usuariosmenu.R;
import com.romelapj.usuariosmenu.model.Usuario;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by romelapj on 14/06/15.
 */
public class UserAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Usuario> items;
    @InjectView(R.id.tvNombre)
    TextView tvNombre;
    @InjectView(R.id.tvUsuario)
    TextView tvUsuario;

    public UserAdapter(Context context, ArrayList items){
        super(context, R.layout.item_usuario,items);
        this.context=context;
        this.items=items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.item_usuario,parent,false);
        ButterKnife.inject(this,rowView);
        tvNombre.setText(items.get(position).getNombre());
        tvUsuario.setText(items.get(position).getUsuario());

        return rowView;

    }
}
