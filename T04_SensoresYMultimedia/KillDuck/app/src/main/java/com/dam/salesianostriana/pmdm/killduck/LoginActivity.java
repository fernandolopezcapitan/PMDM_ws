package com.dam.salesianostriana.pmdm.killduck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button entrar;
    EditText nom_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entrar = (Button) findViewById(R.id.btn_registrar);
        nom_usuario = (EditText) findViewById(R.id.et_nick);

        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();

        if (prefs.getString("clave", null) != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            LoginActivity.this.finish();
        }

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = nom_usuario.getText().toString();
                if(!nick.isEmpty()) {
                    editor.putString("nick",nick);
                    //editor.putInt("hiscore",0);
                    editor.commit();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Campo nick vacío", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
