package com.salesianostriana.dam.pmdm.proyectowebbrowser;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView barra;
    ImageButton favoritos, buscar;
    WebView visor;
    boolean estrella = true;
    ArrayList<String> listafavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Uri webpage = Uri.parse(barra.getText().toString());
        //Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        //startActivity(webIntent);

        listafavoritos = new ArrayList<String>();

        favoritos = (ImageButton) findViewById(R.id.imageButtonFavoritos);
        buscar = (ImageButton) findViewById(R.id.imageButtonBuscar);
        barra = (TextView) findViewById(R.id.editTextBarra);
        visor = (WebView) findViewById(R.id.webViewVisor);

        favoritos.setOnClickListener(this);
        buscar.setOnClickListener(this);

        visor.getSettings().setJavaScriptEnabled(true);
        visor.getSettings().setBuiltInZoomControls(true);

        // Forzamos el webview para que abra los enlaces externos en el navegador
        visor.setWebViewClient(new WebViewClient() {
           @Override
           public boolean shouldOverrideUrlLoading(WebView view, String url) {
               return false;
            }
        });
        barra.setText("google.es");
        visor.loadUrl("http://"+barra.getText().toString());
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
        String message;
        switch (v.getId()){
            case R.id.imageButtonFavoritos:
                //favoritos.setImageDrawable();
                if (estrella){
                    favoritos.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
                    estrella = false;
                    listafavoritos.add(barra.getText().toString());
                    message = barra.getText().toString()+" agregada a favoritos";
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

                } else {
                    favoritos.setImageDrawable(getDrawable(android.R.drawable.star_big_off));
                    estrella = true;
                    listafavoritos.remove(barra.getText().toString());
                    message = barra.getText().toString()+" borrado de favoritos";
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageButtonBuscar:

                    if (listafavoritos.isEmpty())
                    {
                        visor.loadUrl("http://"+barra.getText().toString());
                    } else {
                        for (int i = 0; i < listafavoritos.size(); i++) {
                            if (listafavoritos.get(i).contains(barra.getText().toString())){
                                barra.setText(barra.getText().toString());
                                visor.loadUrl("http://"+barra.getText().toString());
                                favoritos.setImageDrawable(getDrawable(android.R.drawable.star_big_on));
                                estrella = false;
                                break;
                            }else{
                                barra.setText(barra.getText().toString());
                                visor.loadUrl("http://" + barra.getText().toString());
                                favoritos.setImageDrawable(getDrawable(android.R.drawable.star_big_off));
                                estrella = true;
                            }
                        }
                    }

                break;
        }
    }
}
