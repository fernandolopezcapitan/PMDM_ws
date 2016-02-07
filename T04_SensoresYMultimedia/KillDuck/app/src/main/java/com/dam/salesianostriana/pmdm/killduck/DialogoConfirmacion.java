package com.dam.salesianostriana.pmdm.killduck;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dam.salesianostriana.pmdm.killduck.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoConfirmacion extends DialogFragment {

    

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Quiere subir su nueva puntuación?\n20 puntos")
                .setTitle("Confirmacion")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {
                        //Log.i("Dialogos", "Confirmacion Aceptada.");
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



}
