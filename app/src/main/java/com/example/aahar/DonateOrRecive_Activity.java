package com.example.aahar;
/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;


import com.example.aahar.databinding.ActivityDonateOrReciveBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonateOrRecive_Activity extends AppCompatActivity {
    ActivityDonateOrReciveBinding binding;
    FirebaseAuth auth;
    TextView donate,recieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDonateOrReciveBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Welcome");

        //show all user data

          // showAllUserData();
        Intent in = getIntent();
        String user_username = in.getStringExtra("username");
        String user_phoneno = in.getStringExtra("phoneno");
        String user_email = in.getStringExtra("email");
        String user_password = in.getStringExtra("password");

        binding.wantto.setText(user_username);


        auth = FirebaseAuth.getInstance();

        donate = findViewById(R.id.donatefood);
        recieve=findViewById(R.id.recievefood);

        //Donate activity
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(DonateOrRecive_Activity.this, "donate", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DonateOrRecive_Activity.this,Donate_Activity.class);
                intent.putExtra("username",user_username);
                intent.putExtra("phoneno",user_phoneno);
                intent.putExtra("email",user_email);
                intent.putExtra("password",user_password);

                startActivity(intent);

            }
        });

        //Receive Activity
        recieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(DonateOrRecive_Activity.this, "recieve", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DonateOrRecive_Activity.this,ReceiveOne_Activiy.class);
                intent.putExtra("username",user_username);

                startActivity(intent);
            }
        });

    }
    /*Created by Sahil kumar
     * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
     * thankyou*/


    /*private   void showAllUserData() {
        Intent in = getIntent();
        String user_username = in.getStringExtra("username");
        String user_phoneno = in.getStringExtra("phoneno");
        String user_email = in.getStringExtra("email");
        String user_password = in.getStringExtra("password");

        binding.wantto.setText(user_username);
    }*/


    //Menu design
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.setting:
                Toast.makeText(this, "Now we don't add settings in our app ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                auth.signOut();
                Intent intent=new Intent(DonateOrRecive_Activity.this,SignIn_Activity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}