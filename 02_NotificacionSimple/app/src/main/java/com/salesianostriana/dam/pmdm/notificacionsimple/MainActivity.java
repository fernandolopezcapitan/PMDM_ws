package com.salesianostriana.dam.pmdm.notificacionsimple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton btnPlayStop;
    Intent servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPlayStop = (ToggleButton) findViewById(R.id.buttonPlayStop);

        servicio = new Intent(this, NotificationService.class);
        servicio.putExtra("cancion", "Justin Bieber");

        btnPlayStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(servicio);
                } else {
                    stopService(servicio);
                }
            }
        });
    }
}
