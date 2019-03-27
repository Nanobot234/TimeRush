package com.example.harlemknights.TimeRush;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Random;

public class ReflexModeActivity extends AppCompatActivity {


    int score = 0;
    public int updateScore(int s){
        score += s;
        return score;
    }

    public int getScore() {
        return score;
    }


    int x;
    int y;
    int i;
    int j;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_mode);

        final   Button badmonster = (Button) findViewById(R.id.badbutton);
        final Button goodmonster = (Button) findViewById(R.id.goodbutton);
        final   Button reflexmonster = (Button) findViewById(R.id.reflexbutton);
        final TextView reflexing = (TextView) findViewById(R.id.reflexView);
        RelativeLayout l = (RelativeLayout) findViewById(R.id.reflexlayout);


        l.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ReflexModeActivity.this," You lost since you touched the background",Toast.LENGTH_SHORT).show();
                Intent i =  new Intent(ReflexModeActivity.this,ReflexLostGameActivity.class);

                int currentScore = getScore();
//Add your data to bundle


                i.putExtra("MY_Guy", currentScore);

//Fire that second activity
                startActivity(i);


            }
        });
        badmonster.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ReflexModeActivity.this," + 1 points",Toast.LENGTH_SHORT).show();
                reflexing.setText("score: " + updateScore(1));

            }
        });

        goodmonster.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ReflexModeActivity.this," - 1 point",Toast.LENGTH_SHORT).show();
                reflexing.setText("score: " + updateScore(-1));

            }
        });

        reflexmonster.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(ReflexModeActivity.this," + 2 point",Toast.LENGTH_SHORT).show();
                reflexing.setText("score: " + updateScore(2));

            }
        });





        final Handler handler = new Handler();
        Runnable instructions = new Runnable() {
            @Override
            public void run() {


                Random r = new Random();
                x = r.nextInt(1080 - badmonster.getWidth() - 45);
                y = r.nextInt(1920 - badmonster.getHeight() - 45);

                badmonster.setX(x);   // give me error here that setX(int) is not defined
                badmonster.setY(y);





                Random z = new Random();
                i = z.nextInt(1080 - badmonster.getWidth() - 45);
                j = z.nextInt(1920 - badmonster.getHeight() - 45 - 45);

                badmonster.setX(x);   // give me error here that setX(int) is not defined
                badmonster.setY(y+45);



                while( i > (x - badmonster.getWidth()) && i < (x + badmonster.getWidth())) {
                    i = r.nextInt(1080 - badmonster.getWidth());
                    j= r.nextInt(1920 - badmonster.getHeight() - 45);
                }


                goodmonster.setX(i);   // give me error here that setX(int) is not defined
                goodmonster.setY(j);

                //niceMonsterButton.postDelayed(this, 3000);
                handler.postDelayed(this, 600);
            }

        };
        handler.postDelayed(instructions, 600);


        final Handler hand = new Handler();
        Runnable direction = new Runnable() {
            @Override
            public void run() {

                Random n = new Random();
                int e = n.nextInt(1080 - reflexmonster.getWidth());
                int w = n.nextInt(1920 - reflexmonster.getHeight() - 45 - 45);

                if((e ==x || e == i) || (w == y || w == j) ) {
                    e = n.nextInt(1080 - reflexmonster.getWidth());
                    w = n.nextInt(1920 - reflexmonster.getHeight() - 45 - 45);


                } else {

                    reflexmonster.setX(e);
                    reflexmonster.setY(w + 45);
                }

                hand.postDelayed(this, 500);
            }
        };
        hand.postDelayed(direction,500);






    }
}
