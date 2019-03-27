package com.example.harlemknights.TimeRush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class  ReflexLostGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_lost_game);

        TextView setReflexText = (TextView) findViewById(R.id.reflexPlayAgain);
        Button setReButton = (Button) findViewById(R.id.button18);
        Button main2 = (Button) findViewById(R.id.main2);
        TextView goodComment = (TextView) findViewById(R.id.Comment2);

        int number = getIntent().getExtras().getInt("MY_Guy");

        setReflexText.setText("Your Score: " + number);


        if(number <= 0) {
            goodComment.setText("Try Again");
        } else {
            goodComment.setText("Good Try!");
        }

        setReButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(ReflexLostGameActivity.this,ReflexModeActivity.class));

            }
        });

        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReflexLostGameActivity.this,MainActivity.class));
            }
        });


    }
}
