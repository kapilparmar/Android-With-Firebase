package com.example.kapil.firebasegps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword,edtUsername,edtPhoneNumber;
    Button btnSignUp;
    String strUserName, strPassword, strEmail, strPhoneNo;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance();

        edtPassword = findViewById(R.id.etPassword);
        edtEmail = findViewById(R.id.etEmailID);
        edtUsername = findViewById(R.id.etUserName);
        edtPhoneNumber = findViewById(R.id.etNumber);
        btnSignUp = findViewById(R.id.btnLogin);

    }

    public void Continue(View view){

        strUserName = edtUsername.getText().toString().trim();
        strPhoneNo = edtPhoneNumber.getText().toString().trim();
        strEmail= edtEmail.getText().toString().trim();
        strPassword = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(strUserName)) {
            edtUsername.setError("Please Enter User Name");
            return;
        }
       else if (TextUtils.isEmpty(strPhoneNo)) {
            edtPhoneNumber.setError("Please Enter Mobile Number");
            return;
        }
        else  if (TextUtils.isEmpty(strEmail)) {
            edtUsername.setError("Please Enter Email");
            return;
        }
        else if (TextUtils.isEmpty(strPassword)) {
            edtUsername.setError("Please Enter Password");
            return;
        }
        else {

            reference = database.getReference();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(strUserName)){
                        Toast.makeText(SignUpActivity.this,"Username Already taken",Toast.LENGTH_LONG).show();
                        return;
                    }
                    else {
                        Intent intent = new Intent(SignUpActivity.this,UniqueCodeActivity.class);
                        intent.putExtra("data",new UserModel(strUserName,strEmail,strPassword,strPhoneNo));
                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }
}
