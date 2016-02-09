package com.dam.salesianostriana.pmdm.killduck;


import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoConfirmacion extends DialogFragment {

    String nick;
    int puntuacion;

    public DialogoConfirmacion(){

    }

    public DialogoConfirmacion(String nick,int puntuacion){
        this.nick = nick;
        this.puntuacion = puntuacion;
    }

    

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Quiere subir su nueva puntuación?\n\n"+this.puntuacion+" puntos" )
                .setTitle("Confirmacion")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        //Log.i("Dialogos", "Confirmacion Aceptada.");

                        Log.i("DIALOG_NICK ",nick);
                        Log.i("DIALOG_PUNTUACION",String.valueOf(puntuacion));
                        sendDatauser(nick,puntuacion);

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Log.i("Dialogos", "Confirmacion Cancelada.");
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    private void sendDatauser(String nick, int puntuacion) {
        Call<Usuario> hiscoreCall = Servicio.pedirServicio().obtenerUser(nick,String.valueOf(puntuacion));
        hiscoreCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Response<Usuario> response, Retrofit retrofit) {
                //Call<Usuario> result = response.body();

                if (response.code() == 404){
                    Log.d("ERROR404","No se ha podido subir la puntuación");
                }else{
                    Log.d("OK200", "Puntuación actualizada" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


}
