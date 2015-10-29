package com.dam.salesianostriana.di.listviewllamadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LlamadaAdapter extends ArrayAdapter<Llamada> {

    private final Context context;
    private final ArrayList<Llamada> values;

    public LlamadaAdapter(Context context, ArrayList<Llamada> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutLlamadas = inflater.inflate(R.layout.list_item_llamadas, parent, false);

        // Se busca en el layout los elementos que vamos a utilizar.
        ImageView foto = (ImageView) layoutLlamadas.findViewById(R.id.foto);
        TextView numero = (TextView) layoutLlamadas.findViewById(R.id.numero);
        TextView nombre = (TextView) layoutLlamadas.findViewById(R.id.nombre);
        TextView fechayhora = (TextView) layoutLlamadas.findViewById(R.id.fecha);
        TextView tipoLlamada = (TextView) layoutLlamadas.findViewById(R.id.tipoLlamada);

        Llamada llamada = values.get(position);

        foto.setImageResource(llamada.getFoto());
        numero.setText(String.valueOf(llamada.getNumero()));
        nombre.setText(llamada.getNombre());
        fechayhora.setText(llamada.getFechaHora());
        tipoLlamada.setText(llamada.getTipoLlamada());


        return layoutLlamadas;
    }
}
