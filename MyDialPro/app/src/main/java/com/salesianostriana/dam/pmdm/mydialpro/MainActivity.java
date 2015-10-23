package com.salesianostriana.dam.pmdm.mydialpro;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView textView;
    ImageButton btnBorrar, btnllamar;
    String telefono;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnasterisco, btnalmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textViewTelefono);
        Uri data = getIntent().getData();
        if (data != null && ("tel".equals(data.getScheme()))) {
            telefono = PhoneNumberUtils.getNumberFromIntent(getIntent(), this);
        }

        textView.setText(telefono);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn0 = (Button)findViewById(R.id.btn0);
        btnasterisco = (Button)findViewById(R.id.btnasterisco);
        btnalmo = (Button)findViewById(R.id.btnalmo);
        btnllamar = (ImageButton)findViewById(R.id.btnllamar);
        btnBorrar = (ImageButton)findViewById(R.id.buttonBorrar);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnasterisco.setOnClickListener(this);
        btnalmo.setOnClickListener(this);
        btnllamar.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.btn0:
                textView.setText(textView.getText()+"0");
                break;
            case R.id.btn1:
                textView.setText(textView.getText()+"1");
                break;
            case R.id.btn2:
                textView.setText(textView.getText()+"2");
                break;
            case R.id.btn3:
                textView.setText(textView.getText()+"3");
                break;
            case R.id.btn4:
                textView.setText(textView.getText()+"4");
                break;
            case R.id.btn5:
                textView.setText(textView.getText()+"5");
                break;
            case R.id.btn6:
                textView.setText(textView.getText()+"6");
                break;
            case R.id.btn7:
                textView.setText(textView.getText()+"7");
                break;
            case R.id.btn8:
                textView.setText(textView.getText()+"8");
                break;
            case R.id.btn9:
                textView.setText(textView.getText()+"9");
                break;
            case R.id.btnasterisco:
                textView.setText(textView.getText()+"*");
                break;
            case R.id.btnalmo:
                textView.setText(textView.getText()+"#");
                break;
            case R.id.btnllamar:
                String mensaje = "Llamando ...";
                Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonBorrar:
                borrarNumeros(textView, textView.getText().toString());
                break;
        }
    }

    public void borrarNumeros (TextView v, String cadena) {
        // Para borrar el ultimo digito haces esto
        if (!cadena.isEmpty()){
            String cadenaNueva = cadena.substring(0, cadena.length()-1);
            v.setText(cadenaNueva);
        }
    }
}
