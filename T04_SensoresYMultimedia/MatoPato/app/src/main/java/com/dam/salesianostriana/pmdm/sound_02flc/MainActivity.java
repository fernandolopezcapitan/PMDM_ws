package com.dam.salesianostriana.pmdm.sound_02flc;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ImageView pato;
    Button start;
    TextView contador_patos_cazados, txtTiempoRestante;
    int width, height;
    int numAleatorioX, numAleatorioY;
    RelativeLayout layout;
    SoundPool soundPool;
    int sonidoPato, patos_cazados=0;
    boolean contadorActivado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.campo_de_tiro);
        contador_patos_cazados = (TextView) findViewById(R.id.patos_cazados);
        txtTiempoRestante = (TextView) findViewById(R.id.tiempo);
        start = (Button) findViewById(R.id.start);

        pato = new ImageView(this);
        pato.setImageResource(R.drawable.mandarin_duck_icon);

        getAleatorios();
        layout.addView(pato);
        pato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contadorActivado){
                    patos_cazados++;
                    getAleatorios();
                    contador_patos_cazados.setText(String.valueOf(patos_cazados));
                    soundPool.play(sonidoPato, 1, 1, 0, 0, 1);
                }

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patos_cazados = 0;
                contadorActivado = true;
                start.setEnabled(false);
                Toast.makeText(MainActivity.this, "¡Dispara!", Toast.LENGTH_SHORT).show();

                // Cuenta atrás
                new CountDownTimer(60000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        txtTiempoRestante.setText(String.valueOf(millisUntilFinished/1000)+" s.");
                    }

                    @Override
                    public void onFinish() {
                        txtTiempoRestante.setText("0 s.");
                        start.setEnabled(true);
                        Toast.makeText(MainActivity.this, "Has cazado: "+patos_cazados+" patos en 60 s.", Toast.LENGTH_LONG).show();

                        contadorActivado = false;
                        patos_cazados = 0;
                        contador_patos_cazados.setText(String.valueOf(patos_cazados));

                    }
                }.start();

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

        // Cargar sonido de disparo
        sonidoPato = soundPool.load(this,R.raw.soundpato,1);

    }

    public void getAleatorios() {
        width = layout.getWidth()-pato.getWidth();
        height = layout.getHeight()-pato.getHeight();

        numAleatorioX = (int)Math.floor(Math.random()*width);
        numAleatorioY = (int)Math.floor(Math.random()*height);

        pato.setX(numAleatorioX);
        pato.setY(numAleatorioY);
    }

}
