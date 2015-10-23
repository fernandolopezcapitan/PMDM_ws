package com.salesianostriana.dam.pmdm.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class ServicioMusica extends Service {
    public ServicioMusica() {
    }

    // 1. Primer método que se lanza cuando se crea un Servicio
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("SERVICIO","SERVICIO: método onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SERVICIO","SERVICIO: método onStartCommand()");

        // Rescatar datos del intent
        if(intent!=null) {
            Bundle extras = intent.getExtras();
            String cancionAReproducir = extras.getString("cancion");
            Log.i("SERVICIO", "SERVICIO: Reproduciendo " + cancionAReproducir);
        }

        // Si se cancela el servicio no se relanza.
        // return START_NOT_STICKY;

        // Si se cancela el servicio se relanza con Intent nulo
        //return START_STICKY;

        // Si se cancela el servicio y quiero que lance el último Intent
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SERVICIO", "SERVICIO: método onDestroy()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
