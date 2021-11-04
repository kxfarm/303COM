package com.example.eleaveloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerMainPage extends AppCompatActivity {
    private TextView tvName;
    private Button UserProfile, LeaveHistory, LeaveApproval;
    private FirebaseAuth mAuth;
    private DatabaseReference users;
    private FirebaseDatabase db;
    private FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user = mAuth.getCurrentUser();
        tvName = (TextView) findViewById(R.id.name2);


        UserProfile = (Button) findViewById(R.id.btnMUserProfile);
        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfile();
            }
        });

        LeaveHistory = (Button) findViewById(R.id.btnMLeaveHistory);
        LeaveHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveHistory();
            }
        });

        LeaveApproval = (Button) findViewById(R.id.btnApproveLeave);
        LeaveApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveApproval();
            }
        });



    }

    public void userProfile(){
        Intent intent = new Intent(this, ManagerUserProfile.class);
        startActivity(intent);
    };

    public void leaveHistory(){
        Intent intent = new Intent(this, ManagerLeaveHistory.class);
        startActivity(intent);
    };

    public void leaveApproval(){
        Intent intent = new Intent(this, LeaveApproval.class);
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