package com.das.contextos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout lay = (LinearLayout) findViewById(R.id.activity_main);
        TextView tv1 = new TextView(getApplicationContext());
        tv1.setText("Application context.");
        TextView tv2 = new TextView(this);
        tv2.setText("Activity context.");
        lay.addView(tv1);
        lay.addView(tv2);
    }
}
