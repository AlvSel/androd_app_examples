package com.das.das_p1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class miDB extends SQLiteOpenHelper {

    private static  final String DB_NAME= "trabajadores.db";
    private static final int DB_VERSION= 1;

    public miDB(Context ctx, SQLiteDatabase.CursorFactory factory){
        super(ctx, DB_NAME, factory, DB_VERSION);
        //para forzar volver a cargar la db
        //ctx.deleteDatabase(DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Trabajador (Nombre TEXT PRIMARY KEY, Pass TEXT, Email TEXT)");
        db.execSQL("INSERT INTO Trabajador VALUES('admin','admin','admin@admin.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('a','a','a@a.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('b','b','b@b.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('c','c','c@c.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('d','d','d@d.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('e','e','e@e.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('f','f','f@f.com')");
        db.execSQL("INSERT INTO Trabajador VALUES('g','g','g@g.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean validarUsuario(String pUser, String pPass){
        Boolean correcto = false;
        SQLiteDatabase db = getWritableDatabase();

        String[] campos = new String[]{"Nombre"};
        String[] argumentos = new String[]{pUser,pPass};
        Cursor c = db.query("Trabajador",campos,"Nombre=? AND Pass=?",argumentos,null,null,null);

        if(c.moveToFirst()){
            correcto=true;
        }

        c.close();
        db.close();

        return correcto;
    }

    public boolean existeUsuario(String pUser){
        Boolean existe = false;
        SQLiteDatabase db = getWritableDatabase();

        String[] campos = new String[]{"Nombre"};
        String[] argumentos = new String[]{pUser};
        Cursor c = db.query("Trabajador",campos,"Nombre=?",argumentos,null,null,null);

        if(c.moveToFirst()){
            existe=true;
        }

        c.close();
        db.close();

        return existe;
    }

    public void registrarUsuario(String pUser, String pPass, String pEmail){
        SQLiteDatabase db = getWritableDatabase();

        String query = "INSERT INTO Trabajador VALUES('"+pUser+"','"+pPass+"','"+pEmail+"')";
        db.execSQL(query);
        db.close();
    }

    //POST: output[0]=nombres, output[1]=emails
    public ArrayList<ArrayList<String>> getInfo(){
        ArrayList<ArrayList<String>> info= new ArrayList<>();
        ArrayList<String> nombres=new ArrayList<String>();
        ArrayList<String> emails=new ArrayList<String>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Nombre,Email FROM Trabajador",new String[0]);

        while(c.moveToNext()){
            nombres.add(c.getString(0));
            emails.add(c.getString(1));
        }
        c.close();
        db.close();

        info.add(nombres);
        info.add(emails);

        return info;
    }

}
