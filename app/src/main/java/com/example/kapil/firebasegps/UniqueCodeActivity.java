package com.example.kapil.firebasegps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UniqueCodeActivity extends Activity {

    EditText edtCode;
    Button btnsubmit;
    String strCode;
    String strUserName, strPassword, strEmail, strPhoneNo;

    FirebaseDatabase database;
    DatabaseReference ref, refCode;
    UserModel userModel;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_code);
        FirebaseApp.initializeApp(getApplicationContext());
        database = FirebaseDatabase.getInstance();
        sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);

        edtCode = findViewById(R.id.uniquID);
        btnsubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("data");

    }

    public void submitDetails(View view) {

        strCode = edtCode.getText().toString().trim();

        if (TextUtils.isEmpty(strCode)) {
            edtCode.setError("Please Enter Code");
            return;
        } else {
            refCode = database.getReference(userModel.getFullname());

            String key = database.getReference(strCode).push().getKey();

            ref = refCode.child("code");
            ref.setValue(strCode);

            DatabaseReference refPass = refCode.child("password");
            refPass.setValue(userModel.getPassword());
            DatabaseReference refPass1=refCode.child("phone");
            refPass1.setValue(userModel.getPhone());

            DatabaseReference refDetails = refCode.child("Details");
            refDetails.setValue(new UserModel(userModel.getName(),userModel.getFullname(),userModel.getPhone(),userModel.getPassword(),userModel.getCpassword()));

            Intent intent = new Intent(UniqueCodeActivity.this, ActivityDashBoard.class);
            intent.putExtra("userName", userModel.getFullname());
            intent.putExtra("code", strCode);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName",  userModel.getFullname());
            editor.putString("code", strCode);
            editor.commit();

            startActivity(intent);
            finish();

        }

    }
}

