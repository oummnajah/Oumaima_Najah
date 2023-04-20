package com.example.songquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.songquizapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
FirebaseAuth firebaseAuth;

ProgressDialog progressDialog;

    public static ArrayList<Question> questionsList;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_login);
        setContentView(binding.getRoot());



        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getText().toString().trim();
                String password=binding.password.getText().toString().trim();
           progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
               @Override
               public void onSuccess(AuthResult authResult) {
                   progressDialog.cancel();
                   Toast.makeText(LoginActivity.this,"Login succesful",Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(LoginActivity.this,QuizActivity.class));
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
              progressDialog.cancel();
                   Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
               }
           });
                binding.goToSignUpActtivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                });
            }
        });
    }
}