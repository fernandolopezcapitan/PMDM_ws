package com.dam.salesianostriana.pmdm.sound_02flc;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int sonidoPato;
    ImageButton btnPato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPato = (ImageButton)findViewById(R.id.imageButtonPato);
        btnPato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnPato.setPivotX(1);
                //btnPato.setPivotY(1);
                //btnPato.dispatchDrawableHotspotChanged(1,1);
                //soundPool.play(sonidoPato,1,1,0,0,1);
            }
        });

/*
        // Player properties
        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();

        // Load sound of guitar
        sonidoPato = soundPool.load(this,R.raw.soundpato,1);
*/


    }
}
