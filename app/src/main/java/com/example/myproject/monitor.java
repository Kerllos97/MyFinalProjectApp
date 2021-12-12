package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class monitor extends AppCompatActivity {

    private RadioButton FillingRB , LayingRB , CappingRB , BeltRB;
    private DatabaseReference dbr ;
    private String fill , lay , cap , belt;
    private Button  History ;
    private TextView tx1 , tx2 , tx3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        FillingRB   = (RadioButton) findViewById(R.id.FillingRB);
        LayingRB    = (RadioButton) findViewById(R.id.LayingRB) ;
        CappingRB   = (RadioButton) findViewById(R.id.CappingRB);
        BeltRB      = (RadioButton) findViewById(R.id.BeltRB);
        History     = (Button)      findViewById(R.id.history);
        tx1         = (TextView)    findViewById(R.id.textView1);
        tx2         = (TextView)    findViewById(R.id.textView2);
        tx3         = (TextView)    findViewById(R.id.textView3);


        dbr = FirebaseDatabase.getInstance().getReference();

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fill    = dataSnapshot.child("monitor").child("filling").getValue().toString();
                lay     = dataSnapshot.child("monitor").child("laying").getValue().toString();
                cap     = dataSnapshot.child("monitor").child("capping").getValue().toString();
                belt    = dataSnapshot.child("monitor").child("belt").getValue().toString();


                checkvalues (belt , fill , lay , cap );             //check if the part is working and indicate that using the radioButtons

                String s = dataSnapshot.child("Today").child("hours").getValue().toString();
                String y = convertTohours(s);

                tx1.setText(dataSnapshot.child("Today").child("date").getValue().toString());
                tx2.setText(y);
                tx3.setText(dataSnapshot.child("Today").child("products").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryActivity();
            }
        });


    }

    void checkvalues (String belt , String fill , String lay , String cap)
    {
        if (belt.equals("1"))
        {
            BeltRB.setChecked(true);
        }else
        {
            BeltRB.setChecked(false);
        }

        if (fill.equals("1"))
        {
            FillingRB.setChecked(true);
        }else
        {
            FillingRB.setChecked(false);
        }

        if (lay.equals("1"))
        {
            LayingRB.setChecked(true);
        }else
        {
            LayingRB.setChecked(false);
        }

        if (cap.equals("1"))
        {
            CappingRB.setChecked(true);
        }else
        {
            CappingRB.setChecked(false);
        }
    }

    String convertTohours(String s)
    {
        Integer x = Integer.parseInt(s);

        Integer hours   =0 ;
        Integer min     =0;

        hours = x / 3600;
        min   = (x - (hours*3600) )/60 ;

        return (hours.toString() + "h " + min.toString()+"m");

    }

    void HistoryActivity ()
    {
        Intent intent2 = new Intent(this , History.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);

    }
}
