package com.util;

/**
 * Created by jesus on 17/04/2017.
 */

public class Config {
    public int dificultad;
    public int music;
    public int sound;

    public Config(int d, int m, int s){
        dificultad = d;
        music = m;
        sound = s;
    }

    public Config(){
        this(0,0,0);
    }
}
