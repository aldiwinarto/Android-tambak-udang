package com.aldi.tambakudangfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.firebase.client.Firebase;


import com.google.firebase.messaging.FirebaseMessaging;

public class Monitoring extends AppCompatActivity {
    public static final String Firebase_Server_URL = "https://monitoringkolam.firebaseio.com/";


    Button btnMonitoring, btnKontrol, btnabout;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        FirebaseMessaging.getInstance().subscribeToTopic("smartquarium");
        Firebase.setAndroidContext(Monitoring.this);

        btnMonitoring = (Button) findViewById(R.id.btnsuhu);
        btnKontrol = (Button) findViewById(R.id.btndo);

        btnMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(),TabelActivity.class );
                startActivity(i);
            }
        });

        btnKontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), GrafikActivity.class);
                startActivity(i);
            }
        });




    }


}
