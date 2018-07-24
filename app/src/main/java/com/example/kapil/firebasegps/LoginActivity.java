package com.example.kapil.firebasegps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.PasswordAuthentication;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserNAme, edtPassword, edtGroupName;
    Button btnLogin, btnSignUp;
    TextView txtForgotPassword;
    FirebaseDatabase database ;
    DatabaseReference ref;
    String TAG ="Login";
    String strGrpupName, strPassword, strUserNAme, strPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();

        setContentView(R.layout.activity_login);
        edtUserNAme = findViewById(R.id.etEmailID);
        edtPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignup);



    }
    public void ADDLogin(View view){
        strUserNAme= edtUserNAme.getText().toString().trim();
        strPassword = edtPassword.getText().toString().trim();


        ref = database.getReference(strUserNAme);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   String Password = dataSnapshot.child("password").getValue(String.class);
//                    String group = postSnapshot.child("password").getValue(String.class);

                   if (strPassword.equals(Password)){
                       Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                       intent.putExtra("userName",strUserNAme);
                       startActivity(intent);
                       finish();

                   }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public  void  ADDForgotPassWord(View view){

    }

    public  void  SignUp(View view){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
