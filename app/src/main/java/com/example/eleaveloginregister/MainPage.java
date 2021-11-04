package com.example.eleaveloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.CDATASection;

public class MainPage extends AppCompatActivity {
    private TextView tvName;
    private Button UserProfile, ApplyLeave, LeaveHistory, LeaveApproval;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private FirebaseDatabase db;
    private FirebaseUser user;
    private int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user = mAuth.getCurrentUser();
        tvName = (TextView) findViewById(R.id.name);

        UserProfile = (Button) findViewById(R.id.btnUserProfile);
        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile();
            }
        });

        ApplyLeave = (Button) findViewById(R.id.btnApplyLeave);
        ApplyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });

        LeaveHistory = (Button) findViewById(R.id.btnLeaveHistory);
        LeaveHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveHistory();
            }
        });

    }

    public void userProfile(){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    };

    public void applyLeave(){
        Intent intent = new Intent(this, ApplyLeave.class);
        startActivity(intent);
    };

    public void leaveHistory(){
        Intent intent = new Intent(this, LeaveHistory.class);
        startActivity(intent);
    };


    @Override
    protected void onStart() {
        super.onStart();
        DataProfile();
    }

    public void DataProfile(){
        users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvName.setText(snapshot.child("name").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}