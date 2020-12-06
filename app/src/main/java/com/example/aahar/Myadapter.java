package com.example.aahar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/


public class Myadapter extends FirebaseRecyclerAdapter<Child,Myadapter.myViewHolder>
{



    public Myadapter(@NonNull FirebaseRecyclerOptions<Child> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull Child model) {

        holder.textdoanrname.setText(model.getDonorname());
        holder.textfoodname.setText(model.getFoodname());
        holder.textfoodquantity.setText(model.getFoodquantity());
        holder.textphoneno.setText(model.getPhoneno());
        holder.textemail.setText(model.getEmail());

        holder.textaddress.setText(model.getAddress());
      //String latt= String.valueOf(model.getLat());
       // String lann = String.valueOf(model.getLng());
     //  final String address = "geo:"+latt+","+lann;
        holder.latt.setText(String.valueOf(model.getLat()));
        holder.lann.setText(String.valueOf(model.getLng()));
        /*Created by Sahil kumar
         * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
         * thankyou*/


        holder.btnLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.btnLocate.getContext(),NavActivity.class);
              intent.putExtra("latt",model.getLat());
              intent.putExtra("lann",model.getLng());
                holder.btnLocate.getContext().startActivity(intent);
                /*Uri gmmIntentUri = Uri.parse(address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                   context.startActivity(mapIntent);
                }
                else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }*/









            }
        });







    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recivehelp,parent,false);
       return  new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView textdoanrname,textfoodname,textaddress,textfoodquantity,textphoneno,textemail,latt,lann;
        Button btnLocate;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textdoanrname = itemView.findViewById(R.id.textdonarname);
            textfoodname = itemView.findViewById(R.id.textfoodname);
            textfoodquantity=itemView.findViewById(R.id.textfoodquantity);
            textphoneno=itemView.findViewById(R.id.textphoneno);
            textemail=itemView.findViewById(R.id.textemail);
            btnLocate=itemView.findViewById(R.id.locatebtn);

            textaddress = itemView.findViewById(R.id.textaddress);
            latt = itemView.findViewById(R.id.latt);
            lann = itemView.findViewById(R.id.lng);








        }
    }

}
