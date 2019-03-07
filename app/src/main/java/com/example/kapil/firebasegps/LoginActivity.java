package com.example.kapil.firebasegps;
import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends Activity {
    Button b1, b2, b3;
    EditText ed1, ed2, ed3;
    FirebaseDatabase database;
    DatabaseReference ref;
    TextView tx1;
    int counter = 3;
    String strPassword, strUserNAme, strCode;
    ProgressBar progressBar;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());

        database = FirebaseDatabase.getInstance();

        sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.buttonLogin);
        ed1 = findViewById(R.id.editTextnUserName);
        ed2 = findViewById(R.id.editTextPAssword);

        tx1 = findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);
        b3 = findViewById(R.id.buttonSignup);

        progressBar = findViewById(R.id.progressBarLogin);
        progressBar.setVisibility(View.GONE);

    }


    public void onClickLogin(View v) {

        new AsynLogin().execute();

    }


    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);

    }


    public class AsynLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            strUserNAme = ed1.getText().toString();
            strPassword = ed2.getText().toString();


            ref = database.getReference(strUserNAme);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Password = dataSnapshot.child("password").getValue(String.class);
                    String Code = dataSnapshot.child("code").getValue(String.class);

                    if (strPassword.equals(Password)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", strUserNAme);
                        editor.putString("code", Code);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, ActivityDashBoard.class);
                        intent.putExtra("userName", strUserNAme);
                        intent.putExtra("code", Code);
                        startActivity(intent);


                    } else {
                        Toast.makeText(LoginActivity.this, "Not registered!", Toast.LENGTH_LONG).show();


                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;


        }

//        public void insert() {
//            String string= new String(strUserNAme.getText().toString(), strPassword.getText().toString());
//            dbHandler.addUser(string);
//            Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT).show();
//
////set activity_executed inside insert() method.
//            SharedPreferences pref = getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
//            SharedPreferences.Editor edt = pref.edit();
//            edt.putBoolean("activity_executed", true);
//            edt.commit();
//        }


    }
}






