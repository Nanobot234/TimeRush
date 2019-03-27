package com.example.harlemknights.TimeRush;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        final Handler handler = new Handler();

        // Create a runnable (a set of instructions on what to do)
        Runnable instructions = new Runnable() {
            @Override


            public void run() {
                Intent intent  = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                SplashScreenActivity.this.finish();
            }
        };

        // Now give this instruction to the handler
        handler.postDelayed(instructions, 3000);
    }
    }

