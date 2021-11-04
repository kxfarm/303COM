package com.example.eleaveloginregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.UUID;

public class ApplyLeave extends AppCompatActivity {

    private TextView tvName,mStartDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Spinner spLeave, spDuration;
    private EditText etReason;
    private Button ApplyLeave;
    String status = "Pending";
    String uid;


    private FirebaseAuth mAuth;
    private DatabaseReference users, leaveRef;
    private FirebaseDatabase db;
    private FirebaseUser user;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        user=mAuth.getCurrentUser();

        tvName = (TextView) findViewById(R.id.tvName4);
        mStartDate = (TextView) findViewById(R.id.dateStart);
        spDuration = (Spinner) findViewById(R.id.spDuration);
        spLeave = (Spinner) findViewById(R.id.spLeave);
        etReason = (EditText) findViewById(R.id.etReason);
        ApplyLeave = (Button) findViewById(R.id.btnApplyLeave);

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ApplyLeave.this,
                        android.R.style.Theme_Holo_Light_Dialog,
                        mDateSetListener,
                        year, month , day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;

                String date =  dayOfMonth+ "/" + month + "/" + year;
                mStartDate.setText(date);
            }
        };

        ArrayAdapter<String> adapterDuration = new ArrayAdapter<String>(ApplyLeave.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.duration));
        adapterDuration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDuration.setAdapter(adapterDuration);

        ArrayAdapter<String> adapterLeave = new ArrayAdapter<String>(ApplyLeave.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.leave));
        adapterLeave.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLeave.setAdapter(adapterLeave);

        ApplyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyLeave();
            }
        });

    }

    public void applyLeave(){
        if(mStartDate.getText().toString().equals("Date Start")){
            mStartDate.setError("");
        }else if (etReason.getText().toString().equals("")){
            etReason.setError("Enter the Reason");
        }else{
            FirebaseUser leaves = FirebaseAuth.getInstance().getCurrentUser();
            uid = leaves.getUid();
            UUID generateLeaveUid = UUID.randomUUID();

            mdatabase = FirebaseDatabase.getInstance().getReference();
            DatabaseReference child = mdatabase.child("Leave");

            Leave leave = new Leave();

            leave.setName(tvName.getText().toString().trim());
            leave.setLeaveStartDate(mStartDate.getText().toString().trim());
            leave.setLeaveDuration(spDuration.getSelectedItem().toString().trim());
            leave.setLeaveType(spLeave.getSelectedItem().toString().trim());
            leave.setLeaveReason(etReason.getText().toString().trim());
            leave.setStatus(status);
            leave.setUid(uid);
            leave.setLeaveuid(generateLeaveUid.toString());

            child.child(generateLeaveUid.toString()).setValue(leave);

            Toast.makeText(ApplyLeave.this, "Leave Applied Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainPage.class));

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
                tvName.setText(snapshot.child("name").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}