package com.example.aahar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aahar.Models.Users;
import com.example.aahar.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    /*Created by Sahil kumar
     * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
     * thankyou*/

    TextView HaveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        binding =ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set top of app
        getSupportActionBar().setTitle("Register");

        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SignUp_Activity.this);
        progressDialog.setTitle("creating account");
        progressDialog.setMessage("we are creating your account");

        binding.registerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(
                        binding.signUpEmail.getText().toString(), binding.signUpPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            /*Created by Sahil kumar
                             * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
                             * thankyou*/

                            //set values to thhe database
                            Users users = new Users(binding.signupName.getText().toString(),binding.signUpEmail.getText().toString(),binding.signUpPassword.getText().toString(),
                                                    binding.SignUpPhone.getText().toString());

                            String id= task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(users);


                            Toast.makeText(SignUp_Activity.this, "User created sucessfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(SignUp_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        HaveLogin = findViewById(R.id.alreadyacclogin);
        HaveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this,SignIn_Activity.class);
                startActivity(intent);
            }
        });

    }


}