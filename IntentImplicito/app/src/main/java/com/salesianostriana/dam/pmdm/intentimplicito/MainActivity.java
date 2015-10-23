package com.salesianostriana.dam.pmdm.intentimplicito;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btnDialer, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialer = (Button) findViewById(R.id.buttonDialer);
        btnDialer.setOnClickListener(this);

        btnCall = (Button) findViewById(R.id.buttonCall);
        btnCall .setOnClickListener(this);
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
        int idBoton = v.getId();
        Uri number = Uri.parse("tel:5551234");

        switch (idBoton){
            case R.id.buttonDialer:
                Intent dialPhone = new Intent(Intent.ACTION_DIAL, number);
                startActivity(dialPhone);
                break;

            case R.id.buttonCall:
                Intent callPhone = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callPhone);
                break;
        }


    }
}
