package com.aldi.tambakudangfinal;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
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


public class Kontrol extends AppCompatActivity {
    TextView status ;
    Button btnON, btnKontrol, btnOff;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public  DatabaseReference myRef = database.getReference("Aerator");
    public  DatabaseReference myRefmode = database.getReference("MODE");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol);
        btnKontrol = (Button) findViewById(R.id.btnkontrol);
        btnON = (Button) findViewById(R.id.btnon);
        btnOff = (Button) findViewById(R.id.btnoff);
        status  = (TextView) findViewById(R.id.Aerator);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                status.setText("AERATOR : "+value);
                Log.d(TAG, "AERATOR :  " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Write a message to the database
                // FirebaseDatabase database = FirebaseDatabase.getInstance();
                // DatabaseReference myRef = database.getReference("Actuator");

                myRef.setValue("ON");
                myRefmode.setValue("1");

            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Write a message to the database
                // FirebaseDatabase database = FirebaseDatabase.getInstance();
                //  DatabaseReference myRef = database.getReference("Actuator");

                myRef.setValue("OFF");
                myRefmode.setValue("0");

            }
        });

    }

}
