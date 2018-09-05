package com.das.diccionario;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String language="";
    String TAG="AVISO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            language = savedInstanceState.getString("lang");
            changeLang(language);
        }

        setContentView(R.layout.activity_main);

        Button butEs= (Button) findViewById(R.id.butEs);
        butEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language="es";
                changeLang("es");
            }
        });

        Button butEu= (Button) findViewById(R.id.butEu);
        butEu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language="eu";
                Log.d(TAG,"esto vale: "+language);
                changeLang("eu");
            }
        });

        Button butEn= (Button) findViewById(R.id.butEn);
        butEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language="en";
                changeLang("en");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "pause valiendo: "+language);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop valiendo: "+language);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "destroy valiendo: "+language);
    }

    public void changeLang(String pLang){
        Log.d(TAG, "esta mierda vale: "+language);
        Locale nuevaloc = new Locale(pLang);
        Locale.setDefault(nuevaloc);
        Configuration config = new Configuration();
        config.setLocale(nuevaloc);
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG,"guardando: "+language.toString());
        savedInstanceState.putString("lang",language.toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        language = savedInstanceState.getString("lang");
        changeLang(language);
    }
}
