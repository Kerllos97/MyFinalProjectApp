package com.example.myproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class password extends AppCompatActivity {

    private EditText tx1   ;
    private EditText tx2   ;
    private Button   btn1  ;
    private DatabaseReference dbr ;
     String str ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        dbr      =  FirebaseDatabase.getInstance().getReference();
        tx1      =  (EditText)    findViewById(R.id.editText1);
        tx2      =  (EditText)    findViewById(R.id.editText2);
        btn1     =  (Button)    findViewById(R.id.button1);


        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 str =   dataSnapshot.child("Password").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(tx2.getText().toString()))
                {

                    Toast.makeText(getApplicationContext() , "Write new password" , Toast.LENGTH_SHORT).show();
                }else
                {compare_strings(tx1.getText().toString() , str);}

            }
        });

    }
        void compare_strings (String one , String two)
        {
            if(one.equals(two))
            {
                dbr.child("Password").setValue(tx2.getText().toString());
                Toast.makeText(getApplicationContext()," Password Changed" , Toast.LENGTH_SHORT).show();

            }else
            {Toast.makeText(getApplicationContext(),"Incorrect password",Toast.LENGTH_SHORT).show();}
        }


}
