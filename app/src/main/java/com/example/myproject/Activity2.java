package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    private Button bt1  ;
    private Button bt2  ;
    private Button bt3  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        bt1   = (Button)    findViewById(R.id.monitor);
        bt2   = (Button)    findViewById(R.id.button1);
        bt3   = (Button)    findViewById(R.id.settings);





        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlActivity();

            }
        });

        // when click the monitoring button............
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                monitorActivity ();

            }
        });
        //.............................................

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordActivity();
            }
        });


    } // end of on create fun

    void monitorActivity ()
    {
        Intent intent2 = new Intent(this , monitor.class);
        startActivity(intent2);

    }

    void controlActivity ()
    {
        Intent intent = new Intent(this , control.class);
        startActivity(intent);
    }

    void passwordActivity()
    {
        Intent intent3 = new Intent(this , password.class);
        startActivity(intent3);
    }
}
