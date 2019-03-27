package com.example.harlemknights.TimeRush;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.harlemknights.TimeRush.HighScore;
import com.example.harlemknights.TimeRush.MainActivity;
import com.example.harlemknights.TimeRush.PlayGameActivity;
import com.example.harlemknights.TimeRush.R;

public class AfterGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        Button main1 = (Button) findViewById(R.id.MyMain);
        Button playmore = (Button) findViewById(R.id.playAgainButton);
        TextView commentView = (TextView) findViewById(R.id.Comment);
      //  TextView bestScore = (TextView) findViewById(R.id.HighScore);
        playmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AfterGameActivity.this, PlayGameActivity.class));
            }
        });

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AfterGameActivity.this, MainActivity.class));
            }
        });


        int number = getIntent().getExtras().getInt("MY_KEY");

        TextView scoreing = (TextView) findViewById(R.id.ScoreView);

        scoreing.setText(" Your score: " + number);

        /*
        SharedPreferences sp = getSharedPreferences("My_prefs", PlayGameActivity.MODE_PRIVATE);

        int itsHighScore = sp.getInt("My_int_key", HighScore.getHighScore());

        int num = 0;
        int newOne = 0;
        if(itsHighScore > num){
            num  = itsHighScore;
             newOne = num;
            SharedPreferences sew = getSharedPreferences("My_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("My_int_key", newOne);
            editor.commit();

            sew = getSharedPreferences("My_prefs", PlayGameActivity.MODE_PRIVATE);

            int gotit = sp.getInt("My_int_key", newOne);
            bestScore.setText("High Score: " + gotit);
        } else if(num > itsHighScore){
            num = num;
            bestScore.setText("No good");
        }

      */

        //generating your high score
       // if(number > HighScore.getHighScore()) {
         //   HighScore.setHighScore(number);
           // bestScore.setText("High Score: " + HighScore.getHighScore());
      //  } else {
            //  bestScore.setText


        //}

        if (number == 0) {

            commentView.setText("Try again");

        } else {
            commentView.setText("good job");
        }
    }
}