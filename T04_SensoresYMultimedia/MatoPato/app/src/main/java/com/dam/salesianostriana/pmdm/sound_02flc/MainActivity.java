package com.dam.salesianostriana.pmdm.sound_02flc;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    ImageView pato;
    Button start;
    TextView contador_patos_cazados, txtTiempoRestante;
    int width, height;
    int numAleatorioX, numAleatorioY;
    RelativeLayout layout;
    SoundPool soundPool;
    int sonidoPato, patos_cazados=0;
    boolean comienzo;

    //SoundPool soundPool;
    //int sonidoPato;
    //ImageButton btnPato;

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

        pato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sonidoPato,1,1,0,0,1);
                patos_cazados++;
                getAleatorios();
                contador_patos_cazados.setText(String.valueOf(patos_cazados));

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patos_cazados = 0;
                layout.addView(pato);
                start.setEnabled(false);

               // Cuenta atrás
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(new Runnable() {

                    @Override
                    public void run() {
                        double tiempo = 60;
                        double frecuencia = Double.parseDouble(String.valueOf(1)) / 1000;

                        while (tiempo > 0) {
                            tiempo = tiempo - frecuencia;

                            try {
                                Thread.sleep((long) (frecuencia * 1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (tiempo < 0) {
                                tiempo = 0;
                                comienzo = true;

                            } else {
                                actualizarTiempo(txtTiempoRestante, tiempo);
                            }
                        }

                    }
                });
                executorService.shutdown();

                if (comienzo){
                    start.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Has cazado: "+patos_cazados+" patos en 60 s.", Toast.LENGTH_LONG).show();
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

    public void actualizarTiempo(final TextView txt, final double tiempoRestante) {
        txt.post(new Runnable() {
            @Override
            public void run() {
                txt.setText(Integer.toString((int) tiempoRestante) + " s.");
            }
        });
    }
}
