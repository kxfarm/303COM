package com.example.eleaveloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateUserProfile extends AppCompatActivity {
    private EditText etName, etNIC, etPhoneNumber ;
    private TextView tvEmail, tvDepartment,tvPosition;
    private Button updateUser;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private FirebaseDatabase db;
    private FirebaseUser user;


    private DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user = mAuth.getCurrentUser();


        etName = (EditText) findViewById(R.id.name3);
        etNIC = (EditText) findViewById(R.id.nic3);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumber3);

        tvEmail = (TextView) findViewById(R.id.email3);
        tvPosition = (TextView) findViewById(R.id.position3);
        tvDepartment = (TextView) findViewById(R.id.department3);

        updateUser = (Button) findViewById(R.id.btnUpdate3);

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    public void updateUser(){
        if(etName.getText().toString().equals("")){
            etName.setError("Enter Your Name");
        }else if (etNIC.getText().toString().equals("")){
            etNIC.setError("Enter Your NIC Number");
        }else if(etPhoneNumber.getText().toString().equals("")) {
            etPhoneNumber.setError("Enter Your Phone Number");
        }else{

            FirebaseUser user = mAuth.getCurrentUser();
            mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
            DatabaseReference UserDB = mdatabase.child(user.getUid());

            UserDB.child("name").setValue(etName.getText().toString().trim());
            UserDB.child("phoneNum").setValue(etPhoneNumber.getText().toString().trim());
            UserDB.child("nic").setValue(etNIC.getText().toString().trim());

            Toast.makeText(UpdateUserProfile.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), UserProfile.class));

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        DataProfile();
    }

    public void DataProfile(){
        users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvEmail.setText(snapshot.child("email").getValue().toString());
                tvDepartment.setText(snapshot.child("department").getValue().toString());
                tvPosition.setText(snapshot.child("position").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

