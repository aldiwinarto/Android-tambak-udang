package com.aldi.tambakudangfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class TabelSensorSuhu extends Fragment {
    private String Product,temp_key,DO;
    private ListView listsuhu;
    private DatabaseReference root;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> liste = new ArrayList();
private int P=1;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference mySuhu = database.getReference().child("Log Suhu");
    public  DatabaseReference myWaktu = database.getReference("Waktu");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //  FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference mySuhu = database.getReference().child("Log Suhu");
     //  final String key = database.getReference().child("Log Suhu").push().getKey();
        View rootView = inflater.inflate(R.layout.tabel_sensor_suhu, container, false);
        listsuhu = rootView.findViewById(R.id.listView);
       // listsuhu.setAdapter(new ArrayAdapter<String>(rootView.getContext(),
         //       android.R.layout.simple_list_item_1 , liste));
        arrayAdapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_dropdown_item_1line,liste);
        listsuhu.setAdapter(arrayAdapter);
        final Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy HH:mm:ss");
        final String formattedDate=dateFormat.format(currentTime);
        mySuhu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   Set<String> set = new HashSet<>();
                liste.clear();
                arrayAdapter.notifyDataSetChanged();
                P=0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post

                    Float value = postSnapshot.getValue(Float.class);

                    //  Do = (String) ((dataSnapshot).next()).getValue();
                    //set.add(new String(value.toString() + " ºC "));
                    liste.add(P+".)"+ " " +" Suhu : "+value.toString()+"ºC");
                        P=P+1;
                    arrayAdapter.notifyDataSetChanged();
                    Log.d(TAG, "Actuator" + value);
                   // append_chat_conversatin(dataSnapshot);

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });

//        mySuhu.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                append_chat_conversatin(dataSnapshot);
////                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
////                    String  value = dataSnapshot.getValue(String.class);
////                    liste.add(value);
////                    Log.d(TAG, "Actuator" + value);
////                }
////                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                append_chat_conversatin(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        arrayAdapter.notifyDataSetChanged();
        return rootView;
    }
    private Float Do;
    public void append_chat_conversatin(DataSnapshot dataSnapshot) {
        Set<String> set = new HashSet<>();
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext())
        {
            Do =  ((DataSnapshot)i.next()).getValue(Float.class);
          //  Do = (String) ((dataSnapshot).next()).getValue();
            set.add(new String(Do.toString() + " ºC "));

        }
        // list.clear();
        liste.addAll(set);
        arrayAdapter.notifyDataSetChanged();
    }
}
