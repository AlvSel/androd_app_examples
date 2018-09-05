package com.das.scorebowl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EditProfileFragment extends Fragment {

    public EditProfileFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);

        final AppCompatButton editBut = (AppCompatButton) rootView.findViewById(R.id.editBut);
        final TextInputEditText emailET = (TextInputEditText) rootView.findViewById(R.id.newEmailET);

        final String user = Sesion.getSesion().getUserSesion(getActivity());
        try{
            String[] param = new String[1];
            param[0]= user;
            String currentEmail = new DoHTTPRequest(getActivity(),"getEmail", -1, param).execute().get();
            if(currentEmail.compareTo("-1")!=0){
                emailET.setText(currentEmail);
            }
       }catch (Exception e){
            e.printStackTrace();
        }

        editBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailET.getText().length()<1){
                    emailET.setError(getText(R.string.errorFieldRequired));
                }else if(!emailET.getText().toString().contains("@")){
                    emailET.setError(getText(R.string.errorEmail));
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
                    String [] datosE = new String[2];
                    datosE[0] = user;
                    datosE[1] = emailET.getText().toString();
                    String respuesta="1";
                    try{
                        respuesta = new DoHTTPRequest(getActivity(), "updateEmail", -1, datosE).execute().get();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //update OK
                    if(respuesta.compareTo("0")==0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Email actualizado")
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
