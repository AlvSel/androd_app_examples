package com.das.addtask;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> contenido;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenido = new ArrayList<String>();

        if(savedInstanceState != null){
            contenido = savedInstanceState.getStringArrayList("tasks");
        }

        lv = (ListView) findViewById(R.id.myListView);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,contenido);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition= position;
                String itemValue= (String)lv.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Position:"+itemPosition+" "+itemValue,Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogBorrar miDialog = new DialogBorrar(MainActivity.this, adapter, contenido, position);
                miDialog.mostrar();
                return false;
            }
        });
    }

    public void newTask(View pView){
        Intent intent = new Intent(this, AddTask.class);
        startActivityForResult(intent,666);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (requestCode==666){
                android.support.v4.app.NotificationCompat.Builder notif = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle("Notificación de inserción")
                        .setContentText("Se ha añadido "+data.getStringExtra("text"))
                        .setTicker("CAMBIOS");
                NotificationManager nm = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                nm.notify(1, notif.build());

                contenido.add(0,data.getStringExtra("text"));
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putStringArrayList("tasks",contenido);
        super.onSaveInstanceState(savedInstanceState);
    }
}
