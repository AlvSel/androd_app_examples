package com.das.das_p1;

import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.jar.Manifest;

public class NotesActivity extends AppCompatActivity {

    ArrayList<String> contenido;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeChanger.getChanger().changeDefaultTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setTitle(getText(R.string.titleNotes));

        contenido= new ArrayList<String>();
        //cargar notas desde archivo local
        cargarNotes();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,contenido);

        ListView lv = (ListView) findViewById(R.id.notesLV);
        lv.setAdapter(adapter);

        Button but = (Button) findViewById(R.id.notesBut);
        final EditText et = (EditText) findViewById(R.id.notesET);

         but.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //la nota debe contener algun caracter
                 if(et.getText().length()>0){
                     contenido.add(et.getText().toString());
                     //una vez añadido limpia el editText
                     et.setText("");
                     adapter.notifyDataSetChanged();
                 }
                 else{
                     et.setError(getText(R.string.errorNote));
                 }
             }
         });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogBorrar miDialog = new DialogBorrar(NotesActivity.this, adapter, contenido, position);
                miDialog.mostrar();
                return false;
            }
        });
    }

    public void cargarNotes(){
        String line;
        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("notes.txt")));
            while((line= fin.readLine()) !=null){
                contenido.add(line);
            }
            fin.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void guardarNotes(){
        FileOutputStream fos;
        String line;
        try{
            //archivo en memoria interna
            fos = openFileOutput("notes.txt", Context.MODE_PRIVATE);
            for (int i=0;i<contenido.size();i++) {
                line=contenido.get(i)+"\n";
                fos.write(line.getBytes());
            }
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        guardarNotes();
        super.onSaveInstanceState(savedInstanceState);
    }

    //cuando se pierde el foco siempre se llama onPause -> guardamos notas (posiblemente no haga falta hacerlo en onSave)
    @Override
    protected void onPause() {
        super.onPause();
        guardarNotes();
    }
}
