package com.aldi.tambakudangfinal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;

public class GrafikSensorSuhu extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;

Integer P=0;
Integer t=0;
    Integer k=0;

    SimpleDateFormat lengkap = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    SimpleDateFormat jam = new SimpleDateFormat("hh:mm:ss");

    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Log Suhu");
        View rootView = inflater.inflate(R.layout.grafik_sensor_suhu, container, false);GraphView graphView;

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GraphView graphView;

                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
               // series.resetData(dp);

                int index=0;
                P=0;
               // int t;
                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    Integer dataSuhu = myDataSnapshot.getValue(Integer.class);


                    // dp[index] = new DataPoint(dataPH.getTimestamp(), dataPH.getPH());
                    dp[index] = new DataPoint(P,dataSuhu );
                    index++;
                    P=P+1;

                    if (dataSuhu>45){
                        t=t+1;
                        Toast toast = Toast.makeText(getContext(), "Kondisi Suhu Over : "+t, Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);     // set gravity for the Toast.
                        toast.show();
                    }
                    if (dataSuhu<20){
                        k=k+1;
                        Toast toasts = Toast.makeText(getContext(), "Kondisi Suhu Lower : "+k, Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
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

                t=0;
                k=0;
              // t=10;

                //  graphView graph.addSeries(series);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        series = new LineGraphSeries<>(new DataPoint[]{});
//        LineGraphSeries <DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6),
//                new DataPoint(5, 2),
//                new DataPoint(6, 6),
//                new DataPoint(7, 2),
//                new DataPoint(8, 6),
//        });



        graphView = new GraphView(getActivity());

        // GraphView graph = rootView.findViewById(R.id.graph);
        // graphView = new GraphView(getActivity());
        graphView.addSeries(series);
        Viewport viewport = graphView.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(100);
        // viewport.setMaxX(100);
        viewport.setScrollable(true);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        LinearLayout layout =  rootView.findViewById(R.id.grafikSensorSuhu);
        layout.addView(graphView);



        return rootView;
    }

}
