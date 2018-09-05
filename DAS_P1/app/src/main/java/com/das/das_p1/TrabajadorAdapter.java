package com.das.das_p1;

import android.app.Activity;
import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TrabajadorAdapter extends BaseAdapter {

    private ArrayList<String> nombres;
    private ArrayList<String> emails;
    private Context context;
    private static LayoutInflater inflater=null;

    public TrabajadorAdapter(Activity pActivity, ArrayList<String> pNombres, ArrayList<String> pEmails) {
        context=pActivity;
        nombres=pNombres;
        emails=pEmails;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder{
        TextView nombre;
        TextView email;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        View rowView;
        rowView=inflater.inflate(R.layout.list_trabajadores,null);
        holder.nombre=(TextView) rowView.findViewById(R.id.nombreTV);
        holder.email=(TextView) rowView.findViewById(R.id.emailTV);
        holder.nombre.setText(nombres.get(position));
        holder.email.setText(emails.get(position));

        //si se pulsa que envie correo
        Linkify.addLinks(holder.email, Linkify.EMAIL_ADDRESSES);

        return rowView;
    }
}
