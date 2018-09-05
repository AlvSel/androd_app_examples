package com.das.das_p1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DialogBorrar extends AlertDialog {

    final AlertDialog.Builder builder;

    protected DialogBorrar(final Context context, final ArrayAdapter<String> adapter, final ArrayList<String> contenido, final int position) {
        super(context);
        builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.titleDialogBorrar);
        builder.setMessage(R.string.dialogBorrarPregunta);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //borra el item pulsado
                contenido.remove(position);
                //avisa que se ha modificado
                adapter.notifyDataSetChanged();
            }
        });

        //si se pulsa NO -> no hace nada
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    public void mostrar(){
        AlertDialog alert = builder.create();
        alert.show();
    }
}

