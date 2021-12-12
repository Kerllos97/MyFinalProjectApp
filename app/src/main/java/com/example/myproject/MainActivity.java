package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
   private EditText editText ;
   private  Button   button   ;
   private Calendar calendar ;
   private DatabaseReference dbr ;
   private String   currentDate ;
   public int      x=0 ;



   String   pass ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.pass);
        button   = (Button)    findViewById(R.id.enter);
        calendar = Calendar.getInstance();
        dbr      =  FirebaseDatabase.getInstance().getReference();



        checkConnection();       // check Internet connection

        calendar.add(Calendar.DATE , 0);

        currentDate = DateFormat.getDateInstance().format(calendar.getTime());      // set current date

        dbr.child("Today").child("date").setValue(currentDate);                     // save it in the FB

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String LastDate = dataSnapshot.child("Today").child("lastDate").getValue().toString();
                pass            = dataSnapshot.child("Password").getValue().toString();
                if (x == 0) {
                    x= 1 ;                                                              // make this fun. once every time opening the app.
                    if (LastDate.equals(currentDate)) {


                    } else {

                        String hours_String = dataSnapshot.child("Today").child("hours").getValue().toString();

                        String hours = convertTohours (hours_String) ;                   // return new string   ex: 1h2m

                        String products = dataSnapshot.child("Today").child("products").getValue().toString();

                        Long historyNumValue = dataSnapshot.child("History").getChildrenCount();

                        String historyNum = historyNumValue.toString();

                        dbr.child("History").child(historyNum).setValue(LastDate + "                  " + hours + "                " + products);

                        dbr.child("Today").child("lastDate").setValue(currentDate);


                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compare_strings (editText.getText().toString(),pass);       // Compare the string you have write to the saved password

            }
        });
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


    void compare_strings (String one , String two)
    {
        if(one.equals(two))
        {
            Toast.makeText(getApplicationContext()," Password correct" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , Activity2.class);
            startActivity(intent);
        }else
        {Toast.makeText(getApplicationContext(),"Incorrect password",Toast.LENGTH_SHORT).show();}
    }

    void checkConnection ()
    {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork)
        {
            Toast.makeText(this , "Connected to Internet", Toast.LENGTH_SHORT).show();


        }else
        {
            Toast.makeText(this , "No Internet Connection", Toast.LENGTH_SHORT).show();
            editText.setEnabled(false);
            editText.setHint("Check internet connection\n and restart the application");
        }
    }

}


