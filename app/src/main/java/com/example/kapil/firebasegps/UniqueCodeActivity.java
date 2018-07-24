package com.example.kapil.firebasegps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UniqueCodeActivity extends AppCompatActivity {

    EditText edtCode;
    Button btnsubmit;
    String strCode;
    String strUserName, strPassword, strEmail, strPhoneNo;

    FirebaseDatabase database;
    DatabaseReference ref,refCode;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_code);
        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance();


        edtCode = findViewById(R.id.uniquID);
        btnsubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
         userModel = (UserModel) intent.getSerializableExtra("data");

    }

    public void submitDetails(View view){

        strCode = edtCode.getText().toString().trim();

         if (TextUtils.isEmpty(strCode)) {
            edtCode.setError("Please Enter Code");
            return;
        }
        else {
             refCode = database.getReference(userModel.getEmail());

             String key = database.getReference(strCode).push().getKey();

             ref = refCode.child("code");
             ref.setValue(strCode);

             DatabaseReference refPass = refCode.child("password");
             refPass.setValue(userModel.getPassword());

             DatabaseReference refDetails = refCode.child("Details");
             refDetails.setValue(new UserModel(userModel.getEmail(),userModel.getNumber()));
//             ref.setValue(new UserModel(userModel.getName(),userModel.getEmail(),userModel.getPassword(),userModel.getPassword()));

            Intent intent = new Intent(UniqueCodeActivity.this,MainActivity.class);
            intent.putExtra("userName",userModel.getEmail());
            startActivity(intent);
            finish();

        }

    }
}
