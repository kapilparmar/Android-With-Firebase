package com.example.kapil.firebasegps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database ;
    DatabaseReference ref ,ref2 ;
    String TAG ="MAin";
    String key;
    TextView txtName, txtEmai, txtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmai = findViewById(R.id.etEmailID);
        txtName = findViewById(R.id.etUserName);
        txtNumber = findViewById(R.id.etNumber);

        Intent in = getIntent();
        if (in.getExtras()!=null)
            key = in.getStringExtra("userName");

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference(key);
//        ref2 = ref.child(key);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = null, number = null, code, username;
                     code = dataSnapshot.child("code").getValue(String.class);

                    for (DataSnapshot snapshot : dataSnapshot.child("Details").getChildren()){
                         name = snapshot.child("number").getValue(String.class);
                         number = snapshot.child("email").getValue(String.class);

                    }

                    txtEmai.setText(name);
                    txtName.setText(key);
                    txtNumber.setText(number);



//                    String email = userModel.getEmail();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
