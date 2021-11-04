package com.example.eleaveloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button loginNormalUser,loginManagerUser;
    FirebaseAuth mAuth;
    DatabaseReference users;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        loginNormalUser = (Button) findViewById(R.id.btnLoginUser);
        loginManagerUser =(Button)findViewById(R.id.btnLoginUser2);

        loginNormalUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginNormalUser();
            }
        });

        loginManagerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManagerUser();
            }
        });
    }

    private void loginNormalUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required!");;
            etPassword.requestFocus();
        }
        if(password.length()<6){
            etPassword.setError("Min password length is 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                    startActivity(new Intent(Login.this, MainPage.class));

                  }else{
                      Toast.makeText(Login.this, "Failed to login! Please check your credentials!",Toast.LENGTH_SHORT).show();
                  }
                }
        });

    }


    private void loginManagerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Password is required!");;
            etPassword.requestFocus();
        }
        if(password.length()<6){
            etPassword.setError("Min password length is 6 characters!");
            etPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Login.this, ManagerMainPage.class));

                        }else{
                            Toast.makeText(Login.this, "Failed to login! Please check your credentials!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}