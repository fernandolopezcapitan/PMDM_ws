package com.dam.salesianostriana.pmdm.killduck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by flopez on 08/02/2016.
 */
public class RankingAdapter extends ArrayAdapter<Usuario> {

    Context context;
    ArrayList<Usuario> lista;

    public RankingAdapter(Context context, ArrayList<Usuario> objects) {
        super(context, 0, objects);
        this.context = context;
        this.lista = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_players, parent, false);

        Usuario user = lista.get(position);

        TextView posicion = (TextView) v.findViewById(R.id.player_posicion);
        TextView nick = (TextView) v.findViewById(R.id.player_nombre);
        TextView puntuacion = (TextView) v.findViewById(R.id.player_puntuacion);

        posicion.setText(String.valueOf(position + 1));
        nick.setText(user.getNickname());
        puntuacion.setText(user.getPoints());


        return v;
    }
}
