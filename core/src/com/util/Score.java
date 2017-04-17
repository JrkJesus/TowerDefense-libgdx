package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jesus on 17/04/2017.
 */

public class Score {
    private Integer[] leaderboard;

    public Score(Integer score){
        this(new Integer[]{score});
    }

    public Score(){
        this(new Integer[0]);
    }

    public Score(Integer[] l){
        Arrays.sort(l, Collections.reverseOrder());
        if(l.length < 5){
            leaderboard = l;
        }else {
            leaderboard = Arrays.copyOf(l, 5);
        }
    }

    public void addScore(Integer score){
        Integer[] copy = Arrays.copyOf(leaderboard, leaderboard.length+1);
        copy[leaderboard.length] = score;
        Arrays.sort(copy, Collections.reverseOrder());
        if(copy.length < 5){
            leaderboard = copy;
        }else {
            leaderboard = Arrays.copyOf(copy, 5);
        }
    }

    public Integer[] getScore(){
        return leaderboard;
    }
}
