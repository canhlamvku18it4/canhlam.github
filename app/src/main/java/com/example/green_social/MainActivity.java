package com.example.green_social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn", false);
                if (isLoggedIn){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else {
                    isFirstTime();
                }
            }
        },1500);
    }

    private void isFirstTime(){
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTime",false);
            editor.apply();

            //start Onboard activity
            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            finish();
        } else {
            //start Auth activity
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();

        }
    }
}
