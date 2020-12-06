package com.example.aahar;
/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aahar.Models.Users;
import com.example.aahar.databinding.ActivitySignInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn_Activity extends AppCompatActivity {
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth ;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;

    TextView donthaveregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog =new ProgressDialog(SignIn_Activity.this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setMessage("Please wait..");

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        binding.LoginSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.LoginEmail.getText().toString(),binding.LoginPass.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                               if(task.isSuccessful())
                               {


                                   DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

                                   reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                           if(snapshot.exists()){
                                               String id= task.getResult().getUser().getUid();


                                                   //getting values from database

                                                   String emailFromDB = snapshot.child(id).child("email").getValue(String.class);
                                                   String passwordFromDB = snapshot.child(id).child("password").getValue(String.class);
                                                   String usernameFromDB = snapshot.child(id).child("username").getValue(String.class);
                                                   String phonenoFromDB = snapshot.child(id).child("phoneno").getValue(String.class);

                                                   Intent intent  = new Intent(SignIn_Activity.this,DonateOrRecive_Activity.class);
                                                   intent.putExtra("username",usernameFromDB);
                                                   intent.putExtra("phoneno",phonenoFromDB);
                                                   intent.putExtra("email",emailFromDB);
                                                   intent.putExtra("password",passwordFromDB);
                                                   startActivity(intent);



                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error) {


                                       }
                                   });


                               }
                               else {
                                   Toast.makeText(SignIn_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                            }
                        });
            }
        });


        getSupportActionBar().setTitle("Sign In");

        donthaveregister = findViewById(R.id.Haveregister);

        donthaveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignIn_Activity.this,SignUp_Activity.class);
                startActivity(intent);
            }
        });
        binding.GoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }
    int RC_SIGN_IN = 45;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();

                            Users users = new Users();
                            users.setUserId(user.getUid());
                            users.setUsername(user.getDisplayName());
                            users.getProfilepic(user.getPhotoUrl().toString());
                            database.getReference().child("Users").child(user.getUid()).setValue(users);

                            Intent intent = new Intent(SignIn_Activity.this,DonateOrRecive_Activity.class);
                            startActivity(intent);

                            Toast.makeText(SignIn_Activity.this, "Sign in with google", Toast.LENGTH_SHORT).show();
                        //    updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                         //   Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }


}