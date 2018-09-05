package com.das.das_p1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SMSActivity extends AppCompatActivity {

    ArrayList<String> contenido;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChanger.getChanger().changeDefaultTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        // Check permissions:
        int MY_REQUEST_CODE = 666;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_SMS}, MY_REQUEST_CODE);
            }
        }

        contenido= new ArrayList<String>();
        //cargar SMS con el content Provider
        cargarSMS();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,contenido);

        ListView lv = (ListView) findViewById(R.id.smsLV);
        lv.setAdapter(adapter);
    }

    public void cargarSMS(){
        try{
            Uri uriSMSURI = Uri.parse("content://sms/inbox");
            Cursor cur = getContentResolver().query(uriSMSURI,null,null,null,null);

            while (cur.moveToNext()){
                String address = cur.getString(cur.getColumnIndex("address"));
                String body = cur.getString(cur.getColumnIndex("body"));
                contenido.add("Número: "+address+"\nMensaje: "+body);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
