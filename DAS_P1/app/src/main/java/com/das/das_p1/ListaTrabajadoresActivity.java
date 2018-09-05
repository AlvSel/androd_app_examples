package com.das.das_p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaTrabajadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.getChanger().changeDefaultTheme(this);
        setContentView(R.layout.activity_lista_trabajadores);

        /*miDB mD = new miDB(this, null);
        //recupera info de todos trabajadores
        ArrayList<ArrayList<String>> info = mD.getInfo();
        ArrayList<String> nombres=info.get(0);
        ArrayList<String> emails=info.get(1);*/

        ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar3);
        try{
            String respuesta = new DoHTTPRequest(ListaTrabajadoresActivity.this, "info",pb.getId(),  new String[]{}).execute().get();
            ArrayList<String> nombres=new ArrayList<>();
            ArrayList<String> emails=new ArrayList<>();

            JSONArray jo = new JSONArray(respuesta);
            for (int i=0; i < jo.length();i++){
                JSONObject aux = jo.getJSONObject(i);
                nombres.add(aux.getString("Nombre"));
                emails.add(aux.getString("Email"));
            }
            ListView lv = (ListView) findViewById(R.id.trabajadoresLV);
            lv.setAdapter(new TrabajadorAdapter(this, nombres, emails));
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
