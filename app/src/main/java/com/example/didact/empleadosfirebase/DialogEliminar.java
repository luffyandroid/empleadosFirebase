package com.example.didact.empleadosfirebase;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by DIDACT on 01/03/2018.
 */

public class DialogEliminar extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Estas seguro de que deseas eliminar al jugador?")
                .setTitle("ELIMINAR")
                .setPositiveButton("Si, TARJETA ROJA", new DialogInterface.OnClickListener()  {
                    public void onClick(DialogInterface dialog, int id) {



                        Toast.makeText(getActivity(), "Has eliminado al jugador", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "No has eliminado al jugador", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
