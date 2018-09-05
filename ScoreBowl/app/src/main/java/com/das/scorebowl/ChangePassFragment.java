package com.das.scorebowl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ChangePassFragment extends Fragment {

    public ChangePassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_change_pass, container, false);

        final AppCompatButton editBut = (AppCompatButton) rootView.findViewById(R.id.changeBut);
        final EditText currPass = (EditText) rootView.findViewById(R.id.currET);
        final EditText newPass = (EditText) rootView.findViewById(R.id.newPassET);
        final EditText repNewPass = (EditText) rootView.findViewById(R.id.repNewPassET);

        final String user = Sesion.getSesion().getUserSesion(getActivity());

        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currPass.getText().length()<1){
                    currPass.setError(getText(R.string.errorFieldRequired));
                }else if(newPass.getText().length()<1){
                    newPass.setError(getText(R.string.errorFieldRequired));
                }else if(repNewPass.getText().length()<1){
                    repNewPass.setError(getText(R.string.errorFieldRequired));
                }else if(newPass.getText().toString().compareTo(repNewPass.getText().toString())!=0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.dialogPassIguales)
                            .setTitle(R.string.titleAlertDialog)
                            .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else if(!CheckConnection.getCheckConnection().isConnection(getContext())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("ERROR: No hay conexión a internet.")
                            .setTitle(R.string.titleAlertDialog)
                            .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    String [] datosValidar = new String[2];
                    datosValidar[0] = user;
                    datosValidar[1] = currPass.getText().toString();
                    String respuesta="-1";
                    try{
                        respuesta = new DoHTTPRequest(getActivity(), "validar", -1, datosValidar).execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //update OK
                    if(respuesta.compareTo("1")==0){
                        try{
                            String[] datosUpdate = new String[2];
                            datosUpdate[0] = user;
                            datosUpdate[1] = newPass.getText().toString();
                            respuesta = new DoHTTPRequest(getActivity(), "updatePass", -1, datosUpdate).execute().get();
                            if(respuesta.compareTo("0")==0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("Contraseña actualizado")
                                        .setTitle(R.string.titleAlertDialog)
                                        .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("La contraseña no es correcta")
                                .setTitle(R.string.titleAlertDialog)
                                .setNegativeButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });
        return rootView;
    }
}