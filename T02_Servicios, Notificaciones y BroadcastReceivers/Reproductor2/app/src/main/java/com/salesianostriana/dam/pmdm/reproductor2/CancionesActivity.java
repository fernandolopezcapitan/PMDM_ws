package com.salesianostriana.dam.pmdm.reproductor2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class CancionesActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    ImageButton play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canciones);

        lista = (ListView)findViewById(R.id.listView);
        play = (ImageButton)findViewById(R.id.imageButtonPlayVerde);

        ArrayList<ItemCancion> canciones = new ArrayList<ItemCancion>();
        canciones.add(new ItemCancion("Rolling Stones", "Satisfaction", "Greatest Hits", R.drawable.stones, "4:02"));
        canciones.add(new ItemCancion("AC/DC", "Highway to Hell", "Highway to Hell", R.drawable.acdc, "3:30"));
        canciones.add(new ItemCancion("U2", "Beautiful Day", "All That You Can't Leave Behind", R.drawable.u2, "4:10"));

        CancionAdapter adaptador = new CancionAdapter(this,canciones);

        lista.setAdapter(adaptador);

        //getListView().setAdapter(adaptador);
        //lista.getItemAtPosition(0);
        play.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_canciones, menu);
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
    public void onClick(View v) {
        Intent i = new Intent(CancionesActivity.this,ReproductorActivity.class);
        startService(i);
    }
}
