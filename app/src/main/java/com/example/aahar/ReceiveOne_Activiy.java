package com.example.aahar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aahar.Models.Donors;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceiveOne_Activiy extends AppCompatActivity {
 /*   ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Child child;*/
    RecyclerView recview;
    Myadapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_one_activiy);
        recview= (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Child> options =
                new FirebaseRecyclerOptions.Builder<Child>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donars"), Child.class)
                        .build();
        adapter = new Myadapter(options);
        recview.setAdapter(adapter);
        /*Created by Sahil kumar
         * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
         * thankyou*/










        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.warpper,new recfragment()).commit();



      /*  child = new Child();

        listView = (ListView)findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("donars");
        list= new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.recivehelp,R.id.foodrecivemane,list);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        child = ds.getValue(Child.class);
                        list.add(child.getDonorname().toString() + "\n" + child.getFoodname().toString());


                    }
                    listView.setAdapter(adapter);
                }
                else {
                    Toast.makeText(ReceiveOne_Activiy.this, "Now there is no donar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    //  Similarly, the stopListening() call removes the event listener and all data in the adapter. Call this method when the containing Activity or Fragment stops:

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}