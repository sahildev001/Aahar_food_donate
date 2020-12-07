package com.example.aahar;

/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aahar.Models.Donors;

import com.example.aahar.databinding.ActivityDonateBinding;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class Donate_Activity extends AppCompatActivity {
    ActivityDonateBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;

    private FusedLocationProviderClient client;

    Double lat , lng ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start location work
        requestPermissio();
        client = LocationServices.getFusedLocationProviderClient(this);

        //end loaction work
        super.onCreate(savedInstanceState);
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Hey Hunger Hero");

        //gettig data from previous Activity
        Intent in = getIntent();
        String user_username = in.getStringExtra("username");
        String user_phoneno = in.getStringExtra("phoneno");
        String user_email = in.getStringExtra("email");
        String user_password = in.getStringExtra("password");

        binding.etdonorname.setText(user_username);
        binding.etphone.setText(user_phoneno);
        binding.textusername.setText(user_username);
        binding.etemail.setText(user_email);
        /*Created by Sahil kumar
         * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
         * thankyou*/

        binding.setloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //location work start


                if (ActivityCompat.checkSelfPermission(Donate_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Donate_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Donate_Activity.this,new String[]{ACCESS_FINE_LOCATION},1);
                    return;
                }
                client.getLastLocation().addOnSuccessListener(Donate_Activity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {

                            binding.loca.setText(location.toString());
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            //outside code
                            String uid = binding.etphone.getText().toString();
                            Donors donors = new Donors(binding.etdonorname.getText().toString(),
                                    binding.etfoodname.getText().toString(),
                                    binding.etquantity.getText().toString(),
                                    binding.etphone.getText().toString(),
                                    binding.etemail.getText().toString(),
                                    binding.etaddress.getText().toString(), lat, lng);
                            /*//database instance
                            database = FirebaseDatabase.getInstance();
                            String ides = binding.etphone.getText().toString();
                            reference = database.getReference("donars");
                            reference.child(ides).setValue(donors);
                            //database end instance*/


                            Toast.makeText(Donate_Activity.this, "DATA saved", Toast.LENGTH_SHORT).show();
                            //end outside code
                            binding.btndonate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //database instance
                                    database = FirebaseDatabase.getInstance();
                                    String ides = binding.etphone.getText().toString();
                                    reference = database.getReference("donars");
                                    reference.child(ides).setValue(donors);
                                    //database end instance


                                    Toast.makeText(Donate_Activity.this, "Donated", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Donate_Activity.this, DonateOrRecive_Activity.class);
                                    startActivity(intent);

                                }
                            });

                        }

                    }
                });
            //End location workfi






            }
        });
//doante button


//end donate button
    }
    //start
    private void requestPermissio(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }//end




}
