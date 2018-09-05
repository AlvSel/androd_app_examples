package com.das.scorebowl;

import android.content.Context;
import android.content.SharedPreferences;

public class Sesion {
    private static Sesion sesion;

    private Sesion(){}

    public static Sesion getSesion(){
        if(sesion==null){
            sesion=new Sesion();
        }
        return  sesion;
    }

    public void iniciarSesion(Context pCont, String pUser){
        SharedPreferences pref = pCont.getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userSession",pUser);
        editor.commit();
    }

    public void cerrarSesion(Context pCont){
        SharedPreferences pref = pCont.getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userSession",null);
        editor.commit();
    }

    public String getUserSesion(Context pCont){
        SharedPreferences pref = pCont.getSharedPreferences("MyPref",0);
        String usuario = pref.getString("userSession",null);
        return usuario;
    }
}
