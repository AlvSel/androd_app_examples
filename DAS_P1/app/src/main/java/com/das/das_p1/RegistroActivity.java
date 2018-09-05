package com.das.das_p1;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class RegistroActivity extends AppCompatActivity {

    //TODO: dialog con boton para cerrar

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.getChanger().changeDefaultTheme(this);
        setContentView(R.layout.activity_registro);

        Button okBut = (Button) findViewById(R.id.okBut);
        final EditText nomET = (EditText) findViewById(R.id.nameET);
        final EditText emailET = (EditText) findViewById(R.id.emailET);
        final EditText passET = (EditText) findViewById(R.id.passET);
        final EditText repET = (EditText) findViewById(R.id.repET);

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);


        okBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomET.getText().length()<1){
                    nomET.setError(getText(R.string.errorFieldRequired));
                }else if(emailET.getText().length()<1){
                    emailET.setError(getText(R.string.errorFieldRequired));
                }else if(passET.getText().length()<1) {
                    passET.setError(getText(R.string.errorFieldRequired));
                }else if(repET.getText().length()<1) {
                    repET.setError(getText(R.string.errorFieldRequired));
                }else if(!emailET.getText().toString().contains("@")){
                    emailET.setError(getText(R.string.errorEmail));
                }else{
                        miDB mD = new miDB(RegistroActivity.this, null);
                        String [] datosE = new String[1];
                        datosE[0] = nomET.getText().toString();
                        String respuesta="1";
                        try{
                            respuesta = new DoHTTPRequest(RegistroActivity.this, "existe", pb.getId(), datosE).execute().get();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        //nombre de usuario ya existe -> Dialog avisando
                        if(respuesta.compareTo("1")==0){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                            builder.setMessage(R.string.dialogExisteUser)
                                    .setTitle(R.string.titleAlertDialog);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        //passwords no iguales -> Dialog avisando
                        else if(!passET.getText().toString().equals(repET.getText().toString())){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                            builder.setMessage(R.string.dialogPassIguales)
                                    .setTitle(R.string.titleAlertDialog);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        //todos campo OK -> registro + notificacion de bienvenida + Vuelta a Inicio
                        else{
                            String [] datos= new String[3];
                            datos[0]= nomET.getText().toString();
                            datos[1]= passET.getText().toString();
                            datos[2]= emailET.getText().toString();
                            try{
                                String response = new DoHTTPRequest(RegistroActivity.this, "add", pb.getId(), datos).execute().get();
                                /*
                                mD.registrarUsuario(nomET.getText().toString(),passET.getText().toString(),emailET.getText().toString());
                                //Hace falta mD.close() ¿?¿?*/
                                //sin substring devuelve " 0" no funcion trim
                                if(response.substring(1).compareTo("0")==0){
                                    NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(RegistroActivity.this)
                                            .setSmallIcon(android.R.drawable.stat_sys_warning)
                                            .setContentText(getText(R.string.notificationWellcome))
                                            .setContentTitle(getText(R.string.titleNotification));

                                    NotificationManager nM = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                                    nM.notify(1,mBuilder.build());

                                    Intent intent = new Intent(RegistroActivity.this, MenuTrabajadorActivity.class);
                                    startActivity(intent);
                                }else{
                                    Log.d("LOG", "NO");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                                    builder.setMessage("ERROR registrando el usuario. Revise su conexión.")
                                            .setTitle(R.string.titleAlertDialog);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
            }
        });
    }
}
