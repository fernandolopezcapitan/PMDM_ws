package com.dam.salesianostriana.pmdm.killduck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button entrar;
    EditText nom_usuario;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = (Button) findViewById(R.id.btn_registrar);
        nom_usuario = (EditText) findViewById(R.id.et_nick);

        prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        editor = prefs.edit();

        if (prefs.getString("clave", null) != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            LoginActivity.this.finish();
        }

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = nom_usuario.getText().toString();

                if(!nick.isEmpty()) {
                    loadDataRegister(nick);
                } else {
                    Toast.makeText(LoginActivity.this, "Campo nick vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void loadDataRegister(final String nick) {

        final Call<String> loginCall = Servicio.pedirServicio().obtenerRegister(nick);
        loginCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String login = response.body();

                if (response.code() == 404){
                    Toast.makeText(LoginActivity.this, "Fallo de usuario o contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("nick",nick);
                    //editor.putInt("hiscore",0);
                    editor.commit();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


}
