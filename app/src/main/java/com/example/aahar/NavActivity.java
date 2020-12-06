package com.example.aahar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aahar.databinding.ActivityNavBinding;

public class NavActivity extends AppCompatActivity {

    ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("NavigateActivity");
        Intent in = getIntent();
        double lati = in.getDoubleExtra("latt",1);
        double longi = in.getDoubleExtra("lann",1);
        String latt= String.valueOf(lati);
         String lann = String.valueOf(longi);
        /*Created by Sahil kumar
         * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
         * thankyou*/

        final String address = "google.navigation:q="+latt+","+lann+"&mode=l";

        binding.addresstext.setText(address);
        binding.navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(address));

                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                   startActivity(mapIntent);
                }
                else {
                    Toast.makeText(NavActivity.this, "error", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}