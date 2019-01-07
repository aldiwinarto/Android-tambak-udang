package com.aldi.tambakudangfinal;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GrafikSensorPH extends Fragment {
    public int P=0;
    GraphView graphView;
    LineGraphSeries <DataPoint> series;

    FirebaseDatabase database;
    DatabaseReference reference;


    // SimpleDateFormat hari = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    SimpleDateFormat hari = new SimpleDateFormat("MM.dd hh:mm:ss");
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm");


    Integer t=0;
    Integer k=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.grafik_sensor_ph, container, false);GraphView graphView;
        GraphView graph = rootView.findViewById(R.id.graph);
//GraphView graphView;
        series = new LineGraphSeries<>(new DataPoint[]{});
        //    graphView.addSeries(series);





         graphView = new GraphView(getActivity());
        graphView.addSeries(series);
       // graph.addSeries(series);
        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(20);
       // viewport.setMaxX(100);
        viewport.setScrollable(true);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Log DO");
     //   reference = database.getReference("data_sensor");



        graphView.getGridLabelRenderer().setNumHorizontalLabels(10);
//        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
//            @Override
//            public  String formatLabel(double value,boolean isValueX){
//                if(isValueX){
//
//                    return  hari.format(new Date((long) value));
//
//                }else {
//                    return  super.formatLabel(value, isValueX);
//                }
//            }
//        });




        LinearLayout layout =  rootView.findViewById(R.id.grafikSensorPH);
       // GraphView graph = rootView.findViewById(R.id.graph);
       // graph.addSeries(series);
        layout.addView(graphView);






        return rootView;
    }


    @Override
    public void onStart(){
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index=0;
                P=0;
                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    Integer dataPH = myDataSnapshot.getValue(Integer.class);


                   // dp[index] = new DataPoint(dataPH.getTimestamp(), dataPH.getPH());
                    dp[index] = new DataPoint(P,dataPH );
                    index++;
                    P=P+1;

                    if (dataPH>5){
                        t=t+1;
                        Toast toast = Toast.makeText(getContext(), "Kondisi DO Over : "+t, Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);     // set gravity for the Toast.
                        toast.show();
                    }
                    if (dataPH<3){
                        k=k+1;
                        Toast toasts = Toast.makeText(getContext(), "Kondisi D0 Lower : "+k, Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                        toasts.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);     // set gravity for the Toast.
                        toasts.show();
                    }
//                    try {
//                        Thread.sleep(600);
//                    } catch (InterruptedException e) {
//                        // manage error ...
//                    }


                }
                series.resetData(dp);
             //  graphView graph.addSeries(series);
                t=0;
                k=0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}




