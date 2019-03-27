package com.example.harlemknights.TimeRush;

/**
 * Created by Nana Bonsu on 6/6/2017.
 */

public class HighScore {

    private static int HighScore = 0;

    public static void setHighScore(int score){
        HighScore = score;
    }

    public static int getHighScore() {
        return HighScore;
    }


}

