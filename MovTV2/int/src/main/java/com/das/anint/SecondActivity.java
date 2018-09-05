package com.das.anint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void enviarTexto(View pView){
        Intent intent = new Intent();
        EditText et = (EditText) findViewById(R.id.editText);
        intent.putExtra("text", et.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
