package com.aldi.tambakudangfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.firebase.client.Firebase;


import com.google.firebase.messaging.FirebaseMessaging;

public class NewMainActivity extends AppCompatActivity {
    public static final String Firebase_Server_URL = "https://monitoringkolam.firebaseio.com/";


    Button btnMonitoring, btnKontrol, btnabout;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseMessaging.getInstance().subscribeToTopic("smartquarium");
        Firebase.setAndroidContext(NewMainActivity.this);

        btnMonitoring = (Button) findViewById(R.id.btnsuhu);
        btnKontrol = (Button) findViewById(R.id.btndo);
        btnabout = (Button) findViewById(R.id.btnabout);
        btnMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), Monitoring.class);
                startActivity(i);
            }
        });

        btnKontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), Kontrol.class);
                startActivity(i);
            }
        });

        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), About.class);
                startActivity(i);
            }
        });


    }


}
