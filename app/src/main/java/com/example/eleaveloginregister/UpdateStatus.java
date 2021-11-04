package com.example.eleaveloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateStatus extends AppCompatActivity {
    public TextView tvName,tvDuration,tvStartDate,tvReason,tvLeaveType,tvStatus,tvKey;
    public Button btnApprove,btnReject;

    private DatabaseReference databaseRef;

    String key, uid, approve, reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvName = (TextView)findViewById(R.id.tvName6);
        tvDuration = (TextView)findViewById(R.id.tvDuration2);
        tvStartDate = (TextView)findViewById(R.id.tvDateStart);
        tvReason = (TextView)findViewById(R.id.tvReason2);
        tvLeaveType = (TextView)findViewById(R.id.tvLeaveType2);
        tvStatus = (TextView)findViewById(R.id.tvStatus2);

        btnApprove = (Button)findViewById(R.id.btnUpdate);
        btnReject = (Button)findViewById(R.id.btnReject);

        final Object object = getIntent().getSerializableExtra("Key");
        Leave update_status = (Leave) object;
            tvName.setText(update_status.getName());
            tvReason.setText(update_status.getLeaveReason());
            tvStartDate.setText(update_status.getLeaveStartDate());
            tvStatus.setText(update_status.getStatus());
            tvLeaveType.setText(update_status.getLeaveType());
            tvDuration.setText(update_status.getLeaveDuration());
            key = update_status.getLeaveuid();
            uid = update_status.getUid();
            approve = "Approve";
            reject = "Reject";



        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name",tvName.getText().toString());
                hashMap.put("leaveReason",tvReason.getText().toString());
                hashMap.put("leaveStartDate",tvStartDate.getText().toString());
                hashMap.put("leaveDuration",tvDuration.getText().toString());
                hashMap.put("leaveType",tvLeaveType.getText().toString());
                hashMap.put("leaveuid",key);
                hashMap.put("uid",uid);
                hashMap.put("status",approve);

                databaseRef = FirebaseDatabase.getInstance().getReference().child("Leave");
                databaseRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateStatus.this,"SuccessfullyUpdated",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateStatus.this,"Fail Updated",Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(getApplicationContext(), LeaveApproval.class));
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name",tvName.getText().toString());
                hashMap.put("leaveReason",tvReason.getText().toString());
                hashMap.put("leaveStartDate",tvStartDate.getText().toString());
                hashMap.put("leaveDuration",tvDuration.getText().toString());
                hashMap.put("leaveType",tvLeaveType.getText().toString());
                hashMap.put("leaveuid",key);
                hashMap.put("uid",uid);
                hashMap.put("status",approve);

                databaseRef = FirebaseDatabase.getInstance().getReference().child("Leave");
                databaseRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateStatus.this,"SuccessfullyUpdated",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateStatus.this,"Fail Updated",Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(getApplicationContext(), LeaveApproval.class));
            }
        });


    }
}