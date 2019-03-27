package com.example.harlemknights.TimeRush;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harlemknights.TimeRush.HighScore;


import java.util.Random;


public class PlayGameActivity extends AppCompatActivity {


    public abstract class CountDownTimer2 {

        /**
         * Millis since epoch when alarm should stop.
         */
        private final long mMillisInFuture;

        /**
         * The interval in millis that the user receives callbacks
         */
        private final long mCountdownInterval;

        private long mStopTimeInFuture;

        /**
         * @param millisInFuture
         *            The number of millis in the future from the call to
         *            {@link #start()} until the countdown is done and
         *            {@link #onFinish()} is called.
         * @param countDownInterval
         *            The interval along the way to receive {@link #onTick(long)}
         *            callbacks.
         */
        public CountDownTimer2(long millisInFuture, long countDownInterval) {
            mMillisInFuture = millisInFuture;
            mCountdownInterval = countDownInterval;
        }

        /**
         * Cancel the countdown.
         */
        public final void cancel() {
            mHandler.removeMessages(MSG);
        }

        /**
         * Start the countdown.
         */
        public synchronized final CountDownTimer2 start() {
            if (mMillisInFuture <= 0) {
                onFinish();
                return this;
            }
            mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
            mHandler.sendMessage(mHandler.obtainMessage(MSG));
            return this;
        }

        public synchronized void addTime(long millis) {
            mStopTimeInFuture += millis;
        }

        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished
         *            The amount of time until finished.
         */
        public abstract void onTick(long millisUntilFinished);

        /**
         * Callback fired when the time is up.
         */
        public abstract void onFinish();

        private static final int MSG = 1;

        // handles counting down
        private Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                synchronized (CountDownTimer2.this) {
                    final long millisLeft = mStopTimeInFuture
                            - SystemClock.elapsedRealtime();

                    if (millisLeft <= 0) {
                        onFinish();
                    } else if (millisLeft < mCountdownInterval) {
                        // no tick, just delay until done
                        sendMessageDelayed(obtainMessage(MSG), millisLeft);
                    } else {
                        long lastTickStart = SystemClock.elapsedRealtime();
                        onTick(millisLeft);

                        // take into account user's onTick taking time to execute
                        long delay = lastTickStart + mCountdownInterval
                                - SystemClock.elapsedRealtime();

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0)
                            delay += mCountdownInterval;

                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        };
    }

    public class Timer extends CountDownTimer2 {
        private TextView tv;
        public Timer(long millisInFuture, long countDownInterval, TextView tv) {
            super(millisInFuture, countDownInterval);
            this.tv = tv;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long timeLeft = millisUntilFinished / 1000;
            tv.setText("seconds left: " + timeLeft);
        }

        @Override
        public void onFinish() {
            tv.setText("Game Over");
            //supposed to pass the high score value to another screen when its done
            Intent i = new Intent(PlayGameActivity.this, AfterGameActivity.class);
            int currentScore = getScore();
//Add your data to bundle


            i.putExtra("MY_KEY", currentScore);

            HighScore.setHighScore(getScore());

            SharedPreferences sp = getSharedPreferences("My_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("My_int_key", HighScore.getHighScore());
            editor.commit();




//Fire that second activity
            startActivity(i);



        }


    }


    //normal code
    int score = 0;
    public int updateScore(int s){
        score += s;
        return score;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {

        score = score;
    }






    //////////////////////////////////////////////////////////////////////

    //the countDown Time
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        final TextView timeText = (TextView) findViewById(R.id.textView3);
        final TextView pointsText = (TextView) findViewById(R.id.textView4);
        final Button monsterButton = (Button) findViewById(R.id.button3);
        final Button niceMonsterButton = (Button) findViewById(R.id.button4);

            //starting timer
        timer = new Timer(60000, 1000, timeText);
        timer.start();




    //game mechanics
        niceMonsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayGameActivity.this, " - 1 seconds", Toast.LENGTH_SHORT).show();
                timer.addTime(-3000);
            }
        });

        monsterButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(PlayGameActivity.this," + 1 points",Toast.LENGTH_SHORT).show();
                pointsText.setText("score: " + updateScore(1));

            }
        });







        //to move the buttons randomly
        final Handler handler = new Handler();
         Runnable instructions = new Runnable() {
            @Override
            public void run() {


                Random r = new Random();
                int x = r.nextInt(1080 - monsterButton.getWidth());
                int y = r.nextInt(1920 - monsterButton.getHeight() - 75 -75);

                monsterButton.setX(x);   // give me error here that setX(int) is not defined
                monsterButton.setY(y+75);





                Random z = new Random();

                int i = r.nextInt(1080 - monsterButton.getWidth());
                int j = r.nextInt(1920 - monsterButton.getHeight() - 75 - 75);

                while( i > (x - monsterButton.getWidth()) && i < (x + monsterButton.getWidth())) {
                     i = r.nextInt(1080 - monsterButton.getWidth());
                     j= r.nextInt(1920 - monsterButton.getHeight() - 75 - 75);
                }





                niceMonsterButton.setX(i);   // give me error here that setX(int) is not defined
                niceMonsterButton.setY(j + 75);

                //niceMonsterButton.postDelayed(this, 3000);
                handler.postDelayed(this, 750);
            }

    };

    handler.postDelayed(instructions, 750);






    }


 /*   public void onPause() {
        timer.cancel();
        super.onPause();
    }

    */
    public void onBackPressed() {
        timer.cancel();
        startActivity(new Intent(PlayGameActivity.this, MainActivity.class));


    }


}

