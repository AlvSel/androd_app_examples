package com.das.das_p1;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class DoHTTPRequest extends AsyncTask<String, Void, String> {

    // Interfaz:
    public interface AsyncResponse {
        void processFinish(String output, String mReqId);
    }
    public AsyncResponse delegate=null;

    // Variables:
    private static final String TAG = "DoHTTPRequest";

    private Context mContext;
    private String mReqId;
    private String param = "";
    private ProgressBar mProgressBar = null;
    private int mProgressBarId;
    private HttpURLConnection urlConnection = null;
    private String errorMessage = "";

    // Constructor:
    public DoHTTPRequest(Context context, String reqId, int progressBarId, String [] datos) {
        mContext = context;
        mReqId = reqId;
        mProgressBarId = progressBarId;
        errorMessage = "";

        // Poner los datos en formato URL:
        switch(mReqId){
            case "add":
                try {
                    param = "Nombre=" + URLEncoder.encode(datos[0], "UTF-8");
                    param += "&Pass=" + URLEncoder.encode(datos[1], "UTF-8");
                    param += "&Email=" + URLEncoder.encode(datos[2], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "validar":
                try {
                    param = "Nombre=" + URLEncoder.encode(datos[0], "UTF-8");
                    param += "&Pass=" + URLEncoder.encode(datos[1], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "info":
                param="";
                break;
            case "existe":
                try {
                    param = "Nombre=" + URLEncoder.encode(datos[0], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "addImage":
                try {
                    param = "Id=" + URLEncoder.encode(datos[0], "UTF-8");
                    param += "&Data=" + URLEncoder.encode(datos[1], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case "listImages":
                param = "";
                break;
            default:
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (mProgressBarId != -1) {
            mProgressBar = (ProgressBar) ((Activity) mContext).findViewById(mProgressBarId);
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        // Configurar a qu� fichero php se va a llamar desde cada petici�n:
        String st = "http://galan.ehu.eus/asellart002/WEB/";
        switch(mReqId) {
            case "add": st += "add.php"; break;
            case "validar": st += "validarUser.php"; break;
            case "existe": st += "existeUser.php"; break;
            case "info": st += "getInfo.php"; break;
            case "addImage": st += "addImage.php"; break;
            case "listImages": st += "getImages.php"; break;
            default: break;
        }

        // Comprobar si hay conexi�n a Internet:
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (!(netInfo != null && netInfo.isConnected())) {
            errorMessage = "No Internet Connection";
            return errorMessage;
        }

        // Crear la petici�n http:
        String targetURLstr = st;
        InputStream inputStream;
        try {
            // Crear un objeto URL:
            URL targetURL = new URL(targetURLstr);
            urlConnection = (HttpURLConnection) targetURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry());
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setDoOutput(true);

            PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
            out.print(param);
            out.close();

            int statusCode = urlConnection.getResponseCode();

            /* 200 representa "HTTP OK" */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                String result = "";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                // Cerra stream:
                inputStream.close();
                String response = result;
                return response;
            }
            else{
                errorMessage = "Error al conectar con el servidor";
                urlConnection.disconnect();
                return errorMessage;
            }
        } catch (Exception e) {
            errorMessage = "Error al conectar a Internet";
            return errorMessage;
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(final String result) {
        // Finalizar en la activity (mediante los m�todos de la interfaz):
        switch(mReqId){
            case "codigo_01":
                delegate.processFinish(result, mReqId);
                break;
            default:
                break;
        }

        // Esconder la barra de progreso:
        if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    protected void onCancelled() {
        // Esconder la barra de progreso:
        if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
    }

}

