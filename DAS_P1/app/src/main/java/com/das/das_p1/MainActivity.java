package com.das.das_p1;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    EditText userTV;
    EditText passTV;

    public void validar() {
        /*
        miDB mD = new miDB(this, null);
        //login correcto
        if (mD.validarUsuario(userTV.getText().toString(), passTV.getText().toString())) {
            Intent intent = new Intent(MainActivity.this, MenuTrabajadorActivity.class);
            startActivity(intent);
        //aviso error login
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(R.string.dialogLogin)
                    .setTitle(R.string.titleAlertDialog);
            AlertDialog dialog = builder.create();
            dialog.show();
        }*/

        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar2);
        String [] datos= new String[3];
        datos[0]= userTV.getText().toString();
        datos[1]= passTV.getText().toString();
        DoHTTPRequest ht =new DoHTTPRequest(MainActivity.this, "validar", pb.getId(), datos);
        try {
            String respuesta = ht.execute().get();
            if(respuesta.compareTo("1")==0){
                Intent intent = new Intent(MainActivity.this, MenuTrabajadorActivity.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.dialogLogin)
                        .setTitle(R.string.titleAlertDialog);
                AlertDialog dialog = builder.create();
                dialog.show();            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.getChanger().changeDefaultTheme(this);
        setContentView(R.layout.activity_main);
        setTitle(R.string.titleMain);

        final Button okButton = (Button)findViewById(R.id.inicioBut);

        userTV = (EditText) findViewById(R.id.userText);
        passTV = (EditText) findViewById(R.id.passText);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userTV.getText().length()<1){
                    userTV.setError(getText(R.string.errorFieldRequired));
                }
                if(passTV.getText().length()<1){
                    passTV.setError(getText(R.string.errorFieldRequired));
                }
                //todos campos ok
                if(userTV.length()>0 && passTV.length()>0){
                    validar();
                }
            }
        });

        Button regBut = (Button)findViewById(R.id.regBut);
        regBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}

