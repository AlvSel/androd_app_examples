package com.das.addtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void enviarTexto(View pView){
        Intent intent = new Intent();
        EditText et = (EditText) findViewById(R.id.editText);
        intent.putExtra("text", et.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
