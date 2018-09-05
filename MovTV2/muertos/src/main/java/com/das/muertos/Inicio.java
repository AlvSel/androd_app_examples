package com.das.muertos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    public void jugar(View pView){
        Intent intent = new Intent(this, Jugar.class);
        startActivityForResult(intent,666);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button okButton = (Button) findViewById(R.id.jugBut);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jugar(v);
            }
        });
    }
}
