package com.das.das_p1;

import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCalendarEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_event);

        final EditText titulo = (EditText) findViewById(R.id.eventTituloET);
        final EditText desc = (EditText) findViewById(R.id.eventDescET);
        final EditText loc = (EditText) findViewById(R.id.eventLocET);

        final EditText yB = (EditText) findViewById(R.id.yearBegin);
        final EditText mB = (EditText) findViewById(R.id.monthBegin);
        final EditText dB = (EditText) findViewById(R.id.dayBegin);
        final EditText hB = (EditText) findViewById(R.id.horaBegin);
        final EditText minB = (EditText) findViewById(R.id.minBegin);

        final EditText yE = (EditText) findViewById(R.id.yearEnd);
        final EditText mE = (EditText) findViewById(R.id.monthEnd);
        final EditText dE = (EditText) findViewById(R.id.dayEnd);
        final EditText hE = (EditText) findViewById(R.id.horaEnd);
        final EditText minE = (EditText) findViewById(R.id.minEnd);

        Button addBut = (Button)findViewById(R.id.addEventBut);
        addBut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(yB.length()==0){
                    yB.setError(getText(R.string.errorFieldRequired));
                }else if(mB.length()==0) {
                    mB.setError(getText(R.string.errorFieldRequired));
                }else if(dB.length()==0) {
                    dB.setError(getText(R.string.errorFieldRequired));
                }else if(hB.length()==0) {
                    hB.setError(getText(R.string.errorFieldRequired));
                }else if(minB.length()==0) {
                    minB.setError(getText(R.string.errorFieldRequired));
                }else if(yE.length()==0) {
                    yE.setError(getText(R.string.errorFieldRequired));
                }else if(mE.length()==0) {
                    mE.setError(getText(R.string.errorFieldRequired));
                }else if(dE.length()==0) {
                    dE.setError(getText(R.string.errorFieldRequired));
                }else if(hE.length()==0) {
                    hE.setError(getText(R.string.errorFieldRequired));
                }else if(minE.length()==0) {
                    minE.setError(getText(R.string.errorFieldRequired));
                }else{
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(Integer.parseInt(yB.getText().toString()), (Integer.parseInt(mB.getText().toString())-1),
                            Integer.parseInt(dB.getText().toString()), (Integer.parseInt(hB.getText().toString())-1), Integer.parseInt(minB.getText().toString()));
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(Integer.parseInt(yE.getText().toString()), (Integer.parseInt(mE.getText().toString())-1),
                            Integer.parseInt(dE.getText().toString()), (Integer.parseInt(hE.getText().toString())-1), Integer.parseInt(minE.getText().toString()));
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                            .putExtra(CalendarContract.Events.TITLE, titulo.getText().toString())
                            .putExtra(CalendarContract.Events.DESCRIPTION, desc.getText().toString())
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, loc.getText().toString())
                            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                    startActivity(intent);
                }
            }
        });

        Button cancelBut = (Button) findViewById(R.id.eventCancelBut);
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
