package com.dam.salesianostriana.pmdm.sound_02flc;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int sonidoExplosion;
    Button btnSonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSonido = (Button)findViewById(R.id.sonido);
        btnSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.sim933);
                int duration = player.getDuration();

                for(int i=0; i<10; i++) {
                    float left = 1-0.1f*i;
                    float right = 0+0.1f*i;
                    soundPool.play(sonidoExplosion, left, right, i, 0, 1);

                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


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
        sonidoExplosion = soundPool.load(this,R.raw.sim933,1);


    }
}
