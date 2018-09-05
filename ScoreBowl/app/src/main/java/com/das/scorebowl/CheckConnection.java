package com.das.scorebowl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConnection {
    private static CheckConnection checkConnection;

    private CheckConnection() {
    }

    public static CheckConnection getCheckConnection() {
        if (checkConnection == null) {
            checkConnection = new CheckConnection();
        }
        return checkConnection;
    }

    public boolean isConnection(Context pCont) {
        ConnectivityManager cm =
                (ConnectivityManager) pCont.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
