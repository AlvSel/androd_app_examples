package com.das.scorebowl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

//codigo seguido de ejemplo: https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
public class LoginActivity extends AppCompatActivity {

    EditText userTV;
    EditText passTV;

    public void validar() {
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar2);
        String [] datos= new String[3];
        datos[0]= userTV.getText().toString();
        datos[1]= passTV.getText().toString();
        DoHTTPRequest ht =new DoHTTPRequest(LoginActivity.this, "validar", pb.getId(), datos);
        try {
            String respuesta = ht.execute().get();
            if(respuesta.compareTo("1")==0){
                Sesion.getSesion().iniciarSesion(getApplicationContext(),userTV.getText().toString());
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Usuario o contraseña incorrectos.")
                        .setTitle(R.string.titleAlertDialog)
                        .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.titleLogin);

        final Button okButton = (Button)findViewById(R.id.inicioBut);

        userTV = (EditText) findViewById(R.id.userText);
        passTV = (EditText) findViewById(R.id.passText);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userTV.getText().length()<1){
                    userTV.setError(getText(R.string.errorFieldRequired));
                }
                else if(passTV.getText().length()<1){
                    passTV.setError(getText(R.string.errorFieldRequired));
                }else if(!CheckConnection.getCheckConnection().isConnection(getApplicationContext())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("ERROR: No hay conexión a internet.")
                            .setTitle(R.string.titleAlertDialog)
                            .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    validar();
                }
            }
        });

        TextView tv = (TextView)findViewById(R.id.nuevoTV);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
