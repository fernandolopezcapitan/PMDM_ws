package com.salesianostriana.dam.pmdm.notificacionsimple;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationService extends Service {
    NotificationCompat.Builder mBuilder;

    // Definimos un identificador único para las notificaciones
    int mNotificationId = 1;
    NotificationManager mNotifyMgr = null;

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 1. Primer método que se lanza cuando se crea un Servicio
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("SERVICIO", "SERVICIO: método onCreate()");

        // Instancio el servicio NotificationManager,
        // para gestionar notificaciones.
        mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SERVICIO","SERVICIO: método onStartCommand()");

        // Rescatar datos del intent
        if(intent!=null) {
            Bundle extras = intent.getExtras();
            String cancionAReproducir = extras.getString("cancion");
            Log.i("SERVICIO", "SERVICIO: Reproduciendo " + cancionAReproducir);

            mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_notification_sms)
                            .setContentTitle("Reproduciendo")
                            .setContentText(cancionAReproducir);

            // Creo la notificación
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
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

        mBuilder.setContentTitle("Reproductor Parado");
        mBuilder.setContentText("Ninguna");

        // Actualizo la notificación
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
