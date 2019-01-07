package com.aldi.tambakudangfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ControlParameter extends Fragment {
    private EditText PHMin, PHMax, SuhuMin, SuhuMax, waktuIn, waktuOut;
    private Button btnSimpan;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.control_parameter, container, false);
PHMin = (EditText) rootView.findViewById(R.id.edtPHMin);
        PHMax = (EditText) rootView.findViewById(R.id.edtPHMax);
        SuhuMin = (EditText) rootView.findViewById(R.id.edtSuhuMin);
        SuhuMax = (EditText) rootView.findViewById(R.id.edtSuhuMax);
        waktuIn = (EditText) rootView.findViewById(R.id.edtWaktuIn);
        waktuOut = (EditText) rootView.findViewById(R.id.edtWaktuOut);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("parameter");



        return rootView;
    }
}
