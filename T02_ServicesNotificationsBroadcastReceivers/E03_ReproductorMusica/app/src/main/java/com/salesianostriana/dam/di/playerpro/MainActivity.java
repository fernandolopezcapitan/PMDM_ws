package com.salesianostriana.dam.di.playerpro;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements View.OnClickListener{

    ImageButton play;
    boolean star = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canciones_activity);

        ArrayList<ItemCancion> canciones = new ArrayList<ItemCancion>();
        canciones.add(new ItemCancion("Rolling Stones", "Satisfaction", "Greatest Hits", R.drawable.stones, "4:02"));
        canciones.add(new ItemCancion("AC/DC", "Highway to Hell", "Highway to Hell", R.drawable.acdc, "3:30"));
        canciones.add(new ItemCancion("U2", "Beautiful Day", "All That You Can't Leave Behind", R.drawable.u2, "4:10"));

        CancionAdapter adaptador = new CancionAdapter(this,canciones);

        getListView().setAdapter(adaptador);

        play = (ImageButton) findViewById(R.id.btn_play);
        play.setOnClickListener(this);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (star) {
                    play.setImageDrawable(getDrawable(android.R.drawable.ic_media_play));
                    star = false;
                } else {
                    play.setImageDrawable(getDrawable(android.R.drawable.ic_media_pause));
                    star = true;
                }
                break;
        }

    }
}
