package com.salesianostriana.dam.pmdm.listintelefonico;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

    ImageButton btnJuan, btnMaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJuan = (ImageButton) findViewById(R.id.llamarJuan);
        btnMaria = (ImageButton) findViewById(R.id.llamarMaria);

        btnJuan.setOnClickListener(this);
        btnMaria.setOnClickListener(this);

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
        String telefono = "";
        String smsNumber = "";
        if(v.getId()==R.id.llamarJuan) {
            telefono = "954112233";
        } else if(v.getId()==R.id.llamarMaria) {
            telefono = "617225577";
        }

        Uri datos = Uri.parse("tel:"+telefono);
        Intent i = new Intent(Intent.ACTION_DIAL, datos);
        startActivity(i);

        //Uri uri = Uri.parse("smsto:" + smsNumber);
        //Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //intent.putExtra("sms_body", "Hola Mundo");
       // startActivity(intent);
    }
}
