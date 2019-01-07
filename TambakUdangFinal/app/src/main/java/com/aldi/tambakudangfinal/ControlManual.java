package com.aldi.tambakudangfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ControlManual extends Fragment {
    private Button actuatorON ,actuatorOFF;
    private TextView StatusActuator;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public  DatabaseReference myRef = database.getReference("Aerator");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.control_manual, container, false);
        actuatorON = rootView.findViewById(R.id.btnHeater);
        actuatorOFF = rootView.findViewById(R.id.btnKuras);
        StatusActuator = rootView.findViewById(R.id.tvStatusHeater);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                StatusActuator.setText("AERATOR : "+value);
                Log.d(TAG, "AERATOR :  " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        actuatorON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Write a message to the database
               // FirebaseDatabase database = FirebaseDatabase.getInstance();
               // DatabaseReference myRef = database.getReference("Actuator");

                myRef.setValue("ON");

            }
        });
        actuatorOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Write a message to the database
               // FirebaseDatabase database = FirebaseDatabase.getInstance();
              //  DatabaseReference myRef = database.getReference("Actuator");

                myRef.setValue("OFF");

            }
        });
        return rootView;
    }


}
