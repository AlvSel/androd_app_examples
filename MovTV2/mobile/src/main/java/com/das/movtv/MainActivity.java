package com.das.movtv;

import android.os.PersistableBundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG="MainActivity";
    TextView tv;
    String txt="INICIO";
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        tv = (TextView) findViewById(R.id.cuadro);
        if (savedInstanceState!=null){
            txt = savedInstanceState.getString("mensaje");
            cont = savedInstanceState.getInt("cont");
        }
        txt +="->Create";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Start";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cont++;
        Log.d(TAG, "onResume");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Resume";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Pause";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Stop";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Restart";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        tv = (TextView) findViewById(R.id.cuadro);
        txt = tv.getText() +"->Destroy";
        txt += Integer.toString(cont);
        tv.setText(txt);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSave");
        savedInstanceState.putString("mensaje",txt);
        savedInstanceState.putInt("cont",cont);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestore");
        txt = savedInstanceState.getString("mensaje");
        cont = savedInstanceState.getInt("cont");
    }
}
