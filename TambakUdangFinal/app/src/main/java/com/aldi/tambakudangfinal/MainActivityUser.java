package com.aldi.tambakudangfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.firebase.client.Firebase;


import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivityUser extends AppCompatActivity {
    public static final String Firebase_Server_URL = "https://monitoringkolam.firebaseio.com/";


    Button btnGrafik, btnTabel, btnKendali;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);

        FirebaseMessaging.getInstance().subscribeToTopic("smartquarium");
        Firebase.setAndroidContext(MainActivityUser.this);

        btnGrafik = (Button) findViewById(R.id.btnGoGrafik);
        btnTabel = (Button) findViewById(R.id.btnGoTabel);
        btnKendali = (Button) findViewById(R.id.btnControl);
        btnGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), GrafikActivity.class);
                startActivity(i);
            }
        });

        btnTabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), TabelActivity.class);
                startActivity(i);
            }
        });

        btnKendali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), ControlActivity.class);
                startActivity(i);
            }
        });


    }


}
