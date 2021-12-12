package com.example.myproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class control extends AppCompatActivity {

    Switch autoSwitch  , beltSwitch , fillingSwitch , layingSwitch , cappingSwitch ;
    Button btn ;
    DatabaseReference dbr ;
    Integer control = 0 ;
    Integer rand = 1;

    Random r ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        autoSwitch      = (Switch) findViewById(R.id.automaticSwich);                                   // define all the switches in the activity
        beltSwitch      = (Switch) findViewById(R.id.beltSwitch);
        fillingSwitch   = (Switch) findViewById(R.id.fillingSwitch);
        layingSwitch    = (Switch) findViewById(R.id.layingSwitch);
        cappingSwitch   = (Switch) findViewById(R.id.cappingSwitch);
        btn             = (Button) findViewById(R.id.stopBtn);
        dbr             = FirebaseDatabase.getInstance().getReference().child("Control");
        r               = new Random();
        rand            = r.nextInt((100 - 1)+1)+1 ;

        //..........................................................................................

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String controller = dataSnapshot.child("Controller").getValue().toString();
                String rad = rand.toString();
                if (controller.equals("0") && control == 0)
                {

                    dbr.child("Controller").setValue(rad);
                    control = 1 ;
                }else if (controller.equals(rad))
                {
                    autoSwitch.setClickable(true);
                    beltSwitch.setClickable(true);
                    fillingSwitch.setClickable(true);
                    layingSwitch.setClickable(true);
                    cappingSwitch.setClickable(true);

                    control = 1 ;
                }else {
                    autoSwitch.setClickable(false);
                    beltSwitch.setClickable(false);
                    fillingSwitch.setClickable(false);
                    layingSwitch.setClickable(false);
                    cappingSwitch.setClickable(false);
                    autoSwitch.setChecked(false);
                    beltSwitch.setChecked(false);
                    fillingSwitch.setChecked(false);
                    layingSwitch.setChecked(false);
                    cappingSwitch.setChecked(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        // .........................................................................................

        //..........................................................................................
        if (autoSwitch.isClickable()) {
            autoSwitch.setOnClickListener(new View.OnClickListener() {                                  // on click the automatic switch
                @Override
                public void onClick(View view) {
                    if (autoSwitch.isChecked())                                                         // check that the SWITCH is checked or not !
                    {
                        dbr.child("Automatic").setValue("1");
                        dbr.child("Belt").setValue("0");
                        dbr.child("Capping").setValue("0");
                        dbr.child("Filling").setValue("0");
                        dbr.child("Laying").setValue("0");

                        beltSwitch.setChecked(false);
                        fillingSwitch.setChecked(false);
                        layingSwitch.setChecked(false);
                        cappingSwitch.setChecked(false);

                        beltSwitch.setClickable(false);
                        fillingSwitch.setClickable(false);
                        layingSwitch.setClickable(false);
                        cappingSwitch.setClickable(false);

                    } else if (autoSwitch.isChecked() == false) {
                        dbr.child("Automatic").setValue("0");

                        beltSwitch.setClickable(true);
                        fillingSwitch.setClickable(true);
                        layingSwitch.setClickable(true);
                        cappingSwitch.setClickable(true);
                    }
                }
            });
        }
        //..........................................................................................

        if (beltSwitch.isClickable()) {
            beltSwitch.setOnClickListener(new View.OnClickListener() {                                  // on click the switch
                @Override
                public void onClick(View view) {
                    if (beltSwitch.isChecked())                                                         // check that the SWITCH is checked or not !
                    {
                        dbr.child("Belt").setValue("1");
                        dbr.child("Automatic").setValue("0");
                        dbr.child("Capping").setValue("0");
                        dbr.child("Filling").setValue("0");
                        dbr.child("Laying").setValue("0");

                        autoSwitch.setChecked(false);
                        fillingSwitch.setChecked(false);
                        layingSwitch.setChecked(false);
                        cappingSwitch.setChecked(false);

                        autoSwitch.setClickable(false);
                        fillingSwitch.setClickable(false);
                        layingSwitch.setClickable(false);
                        cappingSwitch.setClickable(false);
                    } else{

                        dbr.child("Belt").setValue("0");

                    }
                }
            });
        }
        //..........................................................................................

        if (cappingSwitch.isClickable()) {
            cappingSwitch.setOnClickListener(new View.OnClickListener() {                                  // on click the switch
                @Override
                public void onClick(View view) {
                    if (cappingSwitch.isChecked())                                                         // check that the SWITCH is checked or not !
                    {
                        dbr.child("Capping").setValue("1");
                        dbr.child("Automatic").setValue("0");
                        dbr.child("Belt").setValue("0");
                        dbr.child("Filling").setValue("0");
                        dbr.child("Laying").setValue("0");

                        beltSwitch.setChecked(false);
                        fillingSwitch.setChecked(false);
                        layingSwitch.setChecked(false);
                        autoSwitch.setChecked(false);

                        beltSwitch.setClickable(false);
                        fillingSwitch.setClickable(false);
                        layingSwitch.setClickable(false);
                        autoSwitch.setClickable(false);
                    } else {

                        dbr.child("Capping").setValue("0");
                    }
                }
            });
        }
        //..........................................................................................

        if (layingSwitch.isClickable()) {
            layingSwitch.setOnClickListener(new View.OnClickListener() {                                  // on click the switch
                @Override
                public void onClick(View view) {
                    if (layingSwitch.isChecked())                                                         // check that the SWITCH is checked or not !
                    {
                        dbr.child("Laying").setValue("1");
                        dbr.child("Automatic").setValue("0");
                        dbr.child("Belt").setValue("0");
                        dbr.child("Capping").setValue("0");
                        dbr.child("Filling").setValue("0");


                        beltSwitch.setChecked(false);
                        fillingSwitch.setChecked(false);
                        autoSwitch.setChecked(false);
                        cappingSwitch.setChecked(false);

                        beltSwitch.setClickable(false);
                        fillingSwitch.setClickable(false);
                        autoSwitch.setClickable(false);
                        cappingSwitch.setClickable(false);
                    } else {

                        dbr.child("Laying").setValue("0");
                    }
                }
            });
        }
        //..........................................................................................

        if (fillingSwitch.isClickable()) {
            fillingSwitch.setOnClickListener(new View.OnClickListener() {                                  // on click the switch
                @Override
                public void onClick(View view) {
                    if (fillingSwitch.isChecked())                                                         // check that the SWITCH is checked or not !
                    {
                        dbr.child("Filling").setValue("1");
                        dbr.child("Automatic").setValue("0");
                        dbr.child("Belt").setValue("0");
                        dbr.child("Capping").setValue("0");
                        dbr.child("Laying").setValue("0");

                        beltSwitch.setChecked(false);
                        autoSwitch.setChecked(false);
                        layingSwitch.setChecked(false);
                        cappingSwitch.setChecked(false);

                        beltSwitch.setClickable(false);
                        autoSwitch.setClickable(false);
                        layingSwitch.setClickable(false);
                        cappingSwitch.setClickable(false);
                    } else {

                        dbr.child("Filling").setValue("0");
                    }
                }
            });
        }
        //..........................................................................................

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbr.child("Automatic").setValue("0");
                dbr.child("Belt").setValue("0");
                dbr.child("Capping").setValue("0");
                dbr.child("Filling").setValue("0");
                dbr.child("Laying").setValue("0");
                String rad = rand.toString();
                dbr.child("Controller").setValue(rad);

                autoSwitch.setChecked(false);
                beltSwitch.setChecked(false);
                fillingSwitch.setChecked(false);
                layingSwitch.setChecked(false);
                cappingSwitch.setChecked(false);

            }
        });

    }

    @Override
    public void onDestroy() {

        if (control == 1)
        {
            dbr.child("Controller").setValue("0");
        }
        super.onDestroy();
    }
}
