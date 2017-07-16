package com.sjsu.nguyen.alertsjsu;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAlert extends AppCompatActivity implements View.OnClickListener{

    private Intent mainIntent;

    private EditText etSubject;
    private EditText etContent;
    private EditText etLocation;
    private Button bCreateAlert;
    private Button bDiscardAlert;

    private FirebaseUser mUser;
    private DatabaseReference mFirebaseDatabase;

    private AlertPOJO alertPOJO;

    private AlertDialog.Builder builder;

    private String subject, content, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        etSubject = (EditText) findViewById(R.id.etSubject);
        etContent = (EditText) findViewById(R.id.etContent);
        etLocation = (EditText) findViewById(R.id.etLocation);
        bCreateAlert = (Button) findViewById(R.id.bCreateAlert);
        bDiscardAlert = (Button) findViewById(R.id.bDiscardAlert);

        // Init Firebase Authentication
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        // Set onClickListener for all the button
        bCreateAlert.setOnClickListener(this);
        bDiscardAlert.setOnClickListener(this);

    }

    private void createNewAlert(){

        subject = etSubject.getText().toString();
        content = etContent.getText().toString();
        location = etLocation.getText().toString();

        // Check for user's input, whether it is validating any rules
        if(!validateForm(subject, content)){
            return;
        }

        saveNewAlert(mUser.getUid(), subject, content, location);
        backMain();

    }

    private void saveNewAlert(String uid, String subject, String content, String location){
        alertPOJO = new AlertPOJO(uid, subject, content, location);
        mFirebaseDatabase = mFirebaseDatabase.child("alerts").push();
        mFirebaseDatabase.setValue(alertPOJO);
        System.out.println("ref: " + mFirebaseDatabase.getRef().toString());
        System.out.println("ref's key: " + mFirebaseDatabase.getKey().toString());
        //saveNewValidate(mFirebaseDatabase.getKey().toString());
    }

    /*
    private void saveNewValidate(String key){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabase.child("validates").child(key).setValue(0);
    }
    */

    private boolean validateForm(String subject, String content){

        // Init assuming that the inputs are valid
        boolean valid = true;

        // Use if condition to check for the general rules
        if(TextUtils.isEmpty(subject) || TextUtils.isEmpty(content)){

            // Create/Init builder to show error
            builder = new AlertDialog.Builder(AddAlert.this);
            builder.setMessage("Please enter all fields")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
            valid = false;
        }
        return valid;

    }

    private void backMain(){
        mainIntent = new Intent(AddAlert.this, MainActivity.class);
        AddAlert.this.startActivity(mainIntent);
    }

    // Override method for all the user's action
    @Override
    public void onClick(View v){
        int i =  v.getId();
        if(i == R.id.bCreateAlert)
            createNewAlert();
        if(i == R.id.bDiscardAlert)
            backMain();
    }


}
