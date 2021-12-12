package com.example.myproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ListView history ;
    private ArrayAdapter<String> adapter ;
    private DatabaseReference dbr2 ;
    private ArrayList<String> arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = findViewById(R.id.historyList);
        dbr2 = FirebaseDatabase.getInstance().getReference().child("History");

        arrayList = new ArrayList<>();                                                              // make list to put the history here




        dbr2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                arrayList.clear();

               Long x =dataSnapshot.getChildrenCount();                                             // number of children (Days) which in history
               Integer z = x.intValue();                                                            // Change to int value
               String s ;
               Integer i ;
               for ( i = 0 ; i<z ; i++)                                                             // num. of loops = num of Children
               {
                    s=i.toString();                                                                 // convert the loop iteration to string

                    arrayList.add(dataSnapshot.child(s).getValue().toString()) ;                    // add data to the list

               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


         adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , arrayList);

       history.setAdapter(adapter);


    }


    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_right);
    }


}
