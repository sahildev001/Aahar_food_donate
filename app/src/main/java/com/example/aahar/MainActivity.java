package com.example.aahar;
/*Created by Sahil kumar
 * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
 * thankyou*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button register;
 //   private long backpresstime;
 //   private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        );
        getSupportActionBar().hide();

        login = findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnregister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this,SignIn_Activity.class);
                startActivity(intent);
            }
        });
        /*Created by Sahil kumar
         * for practice if you have any problem to understan this please contact cu.17bcs2904@gmail.com
         * thankyou*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SignUp_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if(keyCode==KeyEvent.KEYCODE_BACK)
           Toast.makeText(getApplicationContext(), "Back press disable by me for better ui", Toast.LENGTH_SHORT).show();
       return false;
    }
    /*  @Override
    public void onBackPressed() {

        if(backpresstime +2000 >System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;

        }
        else{
         backToast=   Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
         backToast.show();
        }
        backpresstime = System.currentTimeMillis();

    }*/
}