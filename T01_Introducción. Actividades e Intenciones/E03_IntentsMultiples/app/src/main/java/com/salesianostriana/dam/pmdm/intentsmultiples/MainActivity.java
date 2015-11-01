package com.salesianostriana.dam.pmdm.intentsmultiples;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnllamar, btnmapa, btnemail;
    TextView num, mapa, destinatario, mensaje, asunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnllamar = (Button) findViewById(R.id.buttonLlamar);
        btnllamar.setOnClickListener(this);
        btnmapa = (Button) findViewById(R.id.buttonMapa);
        btnmapa.setOnClickListener(this);
        btnemail = (Button) findViewById(R.id.buttonEmail);
        btnemail.setOnClickListener(this);

        num = (TextView) findViewById(R.id.editTextNum);
        num.setOnClickListener(this);
        mapa = (TextView) findViewById(R.id.editTextMapa);
        mapa.setOnClickListener(this);
        destinatario = (TextView) findViewById(R.id.editTextDestinatario);
        destinatario.setOnClickListener(this);
        mensaje = (TextView) findViewById(R.id.editTextMensaje);
        mensaje.setOnClickListener(this);
        asunto = (TextView) findViewById(R.id.editTextAsunto);
        asunto.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Uri number = Uri.parse("tel:"+num.getText().toString());
        //Uri number1 = Uri.parse("tel:954112233");
        Uri location = Uri.parse("http://maps.google.co.in/maps?q="+mapa.getText().toString());

        switch (v.getId()) {
            case R.id.buttonLlamar:
                Intent callPhone = new Intent(Intent.ACTION_CALL, number);
                startActivity(callPhone);
                break;
            case R.id.buttonMapa:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
                break;
            case R.id.buttonEmail:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, (destinatario.getText().toString())); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString());
                //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                // You can also attach multiple items by passing an ArrayList of Uris
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
                break;
        }
    }
}
