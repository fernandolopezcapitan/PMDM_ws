package com.dam.salesianostriana.di.listviewllamadas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        llamadas.add(new Llamada(R.drawable.che,650412834,"Che Guevara", "14:28 pm - 02/03/2007","Ll. perdida"));
        llamadas.add(new Llamada(R.drawable.einstein,678354012,"Albert Einstein", "14:20 pm - 02/03/2007","Ll. perdida"));
        llamadas.add(new Llamada(R.drawable.elvis,985671007,"Elvis Presley", "13:02 pm - 02/03/2007","Ll. entrante"));
        llamadas.add(new Llamada(R.drawable.gandhi,678354012,"Mahatma Gandhi", "13:00 pm - 02/03/2007","Ll. perdida"));
        llamadas.add(new Llamada(R.drawable.grace,678354012,"Grace Kelly", "11:58 pm - 01/03/2007","Ll. entrante"));
        llamadas.add(new Llamada(R.drawable.marilyn,678354012,"Marilyn Monroe", "10:00 pm - 01/03/2007","Ll. saliente"));
        llamadas.add(new Llamada(R.drawable.pacino,678354012,"Al Pacino", "20:40 pm - 28/02/2007","Ll. saliente"));
        llamadas.add(new Llamada(R.drawable.picasso,678354012,"Pablo Picasso", "16:50 pm - 28/02/2007","Ll. perdida"));
        llamadas.add(new Llamada(R.drawable.platini,678354012,"Michel Platini", "12:04 pm - 27/02/2007","Ll. entrante"));


        LlamadaAdapter adaptador = new LlamadaAdapter(this, llamadas);

        getListView().setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }
}
