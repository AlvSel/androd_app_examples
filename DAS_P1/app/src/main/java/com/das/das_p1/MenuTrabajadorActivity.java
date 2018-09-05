package com.das.das_p1;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuTrabajadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.getChanger().changeDefaultTheme(this);
        setContentView(R.layout.activity_menu_trabajador);
        setTitle(R.string.titleMenuTrabajador);

        //cada boton lleva a una actividad

        Button compBut = (Button) findViewById(R.id.compBut);
        compBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, ListaTrabajadoresActivity.class);
                startActivity(intent);
            }
        });

        Button settBut = (Button) findViewById(R.id.settBut);
        settBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, PreferenciasActivity.class);
                startActivity(intent);
            }
        });

        Button notasBut = (Button) findViewById(R.id.notasBut);
        notasBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        Button addEvent = (Button) findViewById(R.id.addEventBut);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, AddCalendarEventActivity.class);
                startActivity(intent);
            }
        });

        Button camera = (Button) findViewById(R.id.cameraBut);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        Button images = (Button) findViewById(R.id.imagesBut);
        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, ListaImagesActivity.class);
                startActivity(intent);
            }
        });

        Button sms = (Button) findViewById(R.id.smsBut);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuTrabajadorActivity.this, SMSActivity.class);
                startActivity(intent);
            }
        });
    }
}
