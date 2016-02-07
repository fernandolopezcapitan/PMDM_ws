package com.dam.salesianostriana.pmdm.killduck;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView pato;
    Button start;
    TextView contador_patos_cazados, txtTiempoRestante,hi_score;
    int width, height;
    int numAleatorioX, numAleatorioY;
    RelativeLayout layout;
    SoundPool soundPool;
    int sonidoPato, patos_cazados=0, hiscore=0;
    boolean contadorActivado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.campo_de_tiro);
        contador_patos_cazados = (TextView) findViewById(R.id.patos_cazados);
        txtTiempoRestante = (TextView) findViewById(R.id.tiempo);
        hi_score = (TextView) findViewById(R.id.hi_score);
        start = (Button) findViewById(R.id.start);


        /*SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        int valor = prefs.getInt("hiscore", 0);

        hi_score.setText(valor);
        editor.commit();*/

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
                new CountDownTimer(6000,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        txtTiempoRestante.setText(String.valueOf(millisUntilFinished/1000)+" s.");
                    }

                    @Override
                    public void onFinish() {
                        txtTiempoRestante.setText("0 s.");
                        start.setEnabled(true);


                        Toast.makeText(MainActivity.this, "Has cazado: "+patos_cazados+" patos en 60 s.", Toast.LENGTH_LONG).show();
                        maximaPuntuacion();
                        contadorActivado = false;

                        patos_cazados = 0;
                        contador_patos_cazados.setText(String.valueOf(patos_cazados));

                    }
                }.start();

            }
        });

        // Player properties
        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();

        // Cargar sonido de disparo
        sonidoPato = soundPool.load(this,R.raw.soundpato,1);

    }

    private void maximaPuntuacion() {

        //int hiscore = patos_cazados;

        //SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit();

        if (patos_cazados > hiscore){
            //editor.putInt("hiscore",hiscore);
            hi_score.setText(String.valueOf(patos_cazados));
            hiscore = patos_cazados;
            //editor.commit();

            //FragmentManager fragmentManager = getSupportFragmentManager();
            //DialogoNuevaNota dialogo = new DialogoNuevaNota();
            //DialogoConfirmacion dialogo = new DialogoConfirmacion();
            //dialogo.show(fragmentManager, "tagAlerta");

        }



    }

    public void getAleatorios() {
        width = layout.getWidth()-pato.getWidth();
        height = layout.getHeight()-pato.getHeight();

        numAleatorioX = (int)Math.floor(Math.random()*width);
        numAleatorioY = (int)Math.floor(Math.random()*height);

        pato.setX(numAleatorioX);
        pato.setY(numAleatorioY);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";
        Intent i;
        switch(id) {
            case R.id.hi_score: mensaje = "Puntuaciones";
                i = new Intent(this,ScoreActivity.class);
                i.putExtra("patos_cazados",patos_cazados);

                startActivity(i);
                MainActivity.this.finish();
                break;
            case R.id.logout: mensaje = "Cerrar sesión";
                i = new Intent(this,LoginActivity.class);

                SharedPreferences prefs = getSharedPreferences("preferencias",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

                startActivity(i);
                MainActivity.this.finish();
                break;

        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

}
