package com.das.das_p1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaImagesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_images);
        mRecyclerView = (RecyclerView) findViewById(R.id.imagesRV);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        try{
            String respuesta = new DoHTTPRequest(ListaImagesActivity.this, "listImages",-1,  new String[]{}).execute().get();
            ArrayList<String> ids=new ArrayList<>();
            ArrayList<String> datas=new ArrayList<>();

            JSONArray jo = new JSONArray(respuesta);
            for (int i=0; i < jo.length();i++){
                JSONObject aux = jo.getJSONObject(i);
                ids.add(aux.getString("Id"));
                datas.add(aux.getString("Data"));
            }
            String[] idsArray = new String[ids.size()];
            idsArray = ids.toArray(idsArray);

            String[] datasArray = new String[datas.size()];
            datasArray = datas.toArray(datasArray);

            mAdapter = new ImagesAdapter(idsArray,datasArray);
            mRecyclerView.setAdapter(mAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
