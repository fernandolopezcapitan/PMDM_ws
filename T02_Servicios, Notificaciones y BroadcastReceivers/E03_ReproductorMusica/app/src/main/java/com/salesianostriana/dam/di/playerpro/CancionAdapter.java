package com.salesianostriana.dam.di.playerpro;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class CancionAdapter extends ArrayAdapter<ItemCancion>{

    private final Context context;
    private final AbstractList<ItemCancion> values;

    public CancionAdapter(Context context, ArrayList<ItemCancion> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutCanciones = inflater.inflate(R.layout.list_item_cancion, parent, false);

        // Se busca en el layout los elementos que vamos a utilizar.
        ImageView caratula = (ImageView) layoutCanciones.findViewById(R.id.imageViewCaratula);
        TextView titulo = (TextView) layoutCanciones.findViewById(R.id.TextViewTitulo);
        TextView artista = (TextView) layoutCanciones.findViewById(R.id.TextViewArtista);
        TextView album = (TextView) layoutCanciones.findViewById(R.id.TextViewAlbum);
        TextView duracion = (TextView) layoutCanciones.findViewById(R.id.TextViewDuracion);

        ItemCancion cancion = values.get(position);

        caratula.setImageResource(cancion.getCaratula());
        titulo.setText(cancion.getTituloCancion());
        artista.setText(cancion.getArtista());
        album.setText(cancion.getAlbum());
        duracion.setText(cancion.getDuracion());

        return layoutCanciones;
    }
}
