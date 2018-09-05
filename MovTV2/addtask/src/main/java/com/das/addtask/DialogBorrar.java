package com.das.addtask;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DialogBorrar extends AlertDialog {

    final AlertDialog.Builder builder;

    protected DialogBorrar(@NonNull final Context context, final ArrayAdapter<String> adapter, final ArrayList<String> contenido, final int position) {
        super(context);
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Borrar?");
        builder.setMessage("Seguro que deseas borrar "+contenido.get(position)+"?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.support.v4.app.NotificationCompat.Builder notif = new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle("Notificación de borrado")
                        .setContentText("Se ha borrado "+contenido.get(position))
                        .setTicker("CAMBIOS");
                NotificationManager nm = (NotificationManager) context.getSystemService(context.getApplicationContext().NOTIFICATION_SERVICE);
                nm.notify(1, notif.build());
                contenido.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

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
