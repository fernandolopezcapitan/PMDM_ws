package com.salesianostriana.dam.pmdm.reproductormusica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    String nombreCancionSeleccionada;
    ToggleButton btnPlay;
    RadioButton radioButton;
    int uriCancion = R.raw.michael_buble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton = (RadioButton) findViewById(R.id.seleccionMichaelBuble);
        btnPlay = (ToggleButton) findViewById(R.id.toggleButtonPlay);

        nombreCancionSeleccionada = radioButton.getText().toString();
        radioButton.setSelected(true);

        btnPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent i = new Intent(MainActivity.this,ServicioMusica.class);
                if (isChecked) {
                    i.putExtra("cancion",nombreCancionSeleccionada);
                    i.putExtra("uri",uriCancion);
                    startService(i);
                } else {
                    stopService(i);
                }
            }
        });
    }

    public void seleccionarCancion(View view) {
        int idRadioButton = view.getId();
        radioButton = (RadioButton)view;

        nombreCancionSeleccionada = radioButton.getText().toString();

        if(idRadioButton==R.id.seleccionMichaelBuble) {
            uriCancion = R.raw.michael_buble;
        } else {
            uriCancion = R.raw.sam_smith;
        }

        /*switch (idRadioButton) {
            case R.id.seleccionMichaelBuble:
            break;
            case R.id.seleccionSamSmith: nombreCancionSeleccionada = radioButtom.getText().toString();
        }*/
    }
}
