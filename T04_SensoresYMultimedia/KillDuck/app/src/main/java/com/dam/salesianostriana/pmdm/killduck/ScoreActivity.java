package com.dam.salesianostriana.pmdm.killduck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScoreActivity extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lista = (ListView) findViewById(R.id.listView);
        loadDataRanking();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String mensaje = "";
        Intent i;
        switch(id) {
            case R.id.jugar: mensaje = "Jugar";
                i = new Intent(this,MainActivity.class);

                startActivity(i);
                ScoreActivity.this.finish();
                break;
            case R.id.logout: mensaje = "Cerrar sesión";
                i = new Intent(this,LoginActivity.class);

                SharedPreferences prefs = getSharedPreferences("preferencias",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

                startActivity(i);
                ScoreActivity.this.finish();
                break;

        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    private void loadDataRanking (){

        Call<ArrayList<Usuario>> rankingCall = Servicio.pedirServicio().obtenerRanking();
        rankingCall.enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Response<ArrayList<Usuario>> response, Retrofit retrofit) {
                ArrayList<Usuario> result = response.body();

                if(result != null){
                    lista.setAdapter(new RankingAdapter(ScoreActivity.this,result));
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
