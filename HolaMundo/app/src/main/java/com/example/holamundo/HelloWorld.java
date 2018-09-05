package com.example.holamundo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HelloWorld extends AppCompatActivity {

    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_hello_world);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        TextView tv3 = new TextView(this);
        tv1.setText("LABEL 1");
        tv2.setText("LABEL 2");
        tv3.setText("LABEL 3");

        final LinearLayout firstLay = new LinearLayout(this);
        firstLay.setOrientation(LinearLayout.VERTICAL);
        firstLay.addView(tv1);
        firstLay.addView(tv2);
        firstLay.addView(tv3);
        setContentView(firstLay);

        Button botonCont = new Button(this);
        botonCont.setText("Contador ++");
        firstLay.addView(botonCont);

        final TextView tvCont= new TextView(this);
        tvCont.setText(Integer.toString(contador));
        firstLay.addView(tvCont);

        botonCont.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                contador++;
                tvCont.setText(Integer.toString(contador));
            }
        });

        final LinearLayout secondLay = new LinearLayout(this);
        secondLay.setBackgroundColor(Color.YELLOW);

        Switch switchOne = new Switch(this);
        firstLay.addView(switchOne);

        final EditText edit = new EditText(this);
        firstLay.addView(edit);


        switchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setContentView(secondLay);
                    mensaje(edit.getText().toString());
                } else {
                    setContentView(firstLay);
                }
            }
        });

        Switch switchTwo = new Switch(this);
        secondLay.addView(switchTwo);

        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setContentView(firstLay);
                } else {
                    setContentView(secondLay);
                }
            }
        });
    }

    public void mensaje(String pMensaje) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Alerta Mensaje");

        // set dialog message
        alertDialogBuilder
                .setMessage(pMensaje)
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        HelloWorld.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}

