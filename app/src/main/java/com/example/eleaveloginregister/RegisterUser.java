package com.example.eleaveloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {
    RelativeLayout rootLayout;

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etNic;
    private EditText etPhoneNumber;
    private RadioButton rbDep1;
    private RadioButton rbDep2;
    private RadioButton rbPos1;
    private RadioButton rbPos2;
    private Button  registerUser;
    String department = "";
    String position = "";

    FirebaseAuth mAuth;
    DatabaseReference users;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user2);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");


        registerUser = (Button) findViewById(R.id.btnRegisterUser);

        etName = (EditText) findViewById(R.id.tvName4);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        etNic = (EditText) findViewById(R.id.nic);
        etPhoneNumber = (EditText) findViewById(R.id.tvStatus2);
        rbDep1 = (RadioButton) findViewById(R.id.rbDep1);
        rbDep2 = (RadioButton) findViewById(R.id.rbDep2);
        rbPos1 = (RadioButton) findViewById(R.id.rbPos1);
        rbPos2 = (RadioButton) findViewById(R.id.rbPos2);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }


    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String nic = etNic.getText().toString().trim();
        String phoneNum = etPhoneNumber.getText().toString().trim();
        String dep1 = rbDep1.getText().toString().trim();
        String dep2 = rbDep2.getText().toString().trim();
        String pos1 = rbPos1.getText().toString().trim();
        String pos2 = rbPos2.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Name is required!");
            etName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please provide valid email");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Min password length should be 6 characters!");
            etPassword.requestFocus();
            return;
        }
        if (nic.isEmpty()) {
            etNic.setError("NIC is required!");
            etNic.requestFocus();
            return;
        }
        if (phoneNum.isEmpty()) {
            etPhoneNumber.setError("Phone Number is required!");
            etPhoneNumber.requestFocus();
            return;
        }
        if (dep1.isEmpty() && dep2.isEmpty()) {
            rbDep1.setError("Department is required!");
            rbDep1.requestFocus();
            return;
        }
        if (pos1.isEmpty() && pos2.isEmpty()) {
            rbPos1.setError("Position is required!");
            rbPos1.requestFocus();
            return;
        }

        if (rbDep1.isChecked()) {
            department = "IT";
        }
        if (rbDep2.isChecked()) {
            department = "HR";
        }
        if (rbPos1.isChecked()) {
            position = "Employee";
        }
        if (rbPos2.isChecked()) {
            position = "Manager";
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(
                                    name,
                                    email,
                                    nic,
                                    phoneNum,
                                    department,
                                    position
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RegisterUser.this, "Registration Complete", Toast.LENGTH_SHORT);
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            });
                        } else {
                        }
                    }
                });
    }
}

