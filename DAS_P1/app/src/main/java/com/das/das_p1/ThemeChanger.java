package com.das.das_p1;

//SINGLETON creado para no tener que repetir los pasos en cada onCreate de las Activities
/*TODO: para que funcione se tiene que hacer antes del setContentView de la vista. Hay que esperar
        a que una actividad vuelva a ejecutar onCreate() para ver los cambios*/

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class ThemeChanger {

    private static ThemeChanger miChanger= null;

    private ThemeChanger(){}

    public static ThemeChanger getChanger(){
        if(miChanger==null){
            miChanger=new ThemeChanger();
        }
        return miChanger;
    }

    public void changeDefaultTheme(Activity pA){
        //check tema preferido
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(pA);
        if(sh.contains("temapref")){
            String tema = sh.getString("temapref","default");
            Log.d("PORF", "OK"+tema+pA.getLocalClassName().toString());
            //importante toLowerCase() -> devuelve Default
            if(tema.toLowerCase().equals("default")){
                pA.setTheme(R.style.AppTheme);
            }else{
                pA.setTheme(R.style.Horror);
            }
        }
    }
}
