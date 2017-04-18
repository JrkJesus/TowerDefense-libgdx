package com.util;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


/**
 * Created by jesus on 16/04/2017.
 */

public class XMLReader {

    private static Preferences pref;

    private static Preferences getPref(){
        if(pref == null){
            pref = Gdx.app.getPreferences("mtd-pref");
        }
        return pref;
    }

    public static void initPref(){
        getPref().clear();
        getPref().putInteger("dificultad", 1);
        getPref().putInteger("music", 1);
        getPref().putInteger("sound", 1);
        getPref().putString("leaderboard0", "9000, 500, 3, 50, 65, 98, ");
        getPref().putString("leaderboard1", "300, ");
        getPref().putString("leaderboard2", "5, ");
        getPref().flush();
    }

    public static Config readConfig(){
        return new Config(getPref().getInteger("dificultad"),
                getPref().getInteger("music"),
                getPref().getInteger("sound") );
    }

    public static void changeDificulty(int dificulty){
        getPref().putInteger("dificultad", dificulty);
        getPref().flush();
    }
    public static void changeSound(){
        getPref().putInteger("sound", 1-getPref().getInteger("sound"));
        getPref().flush();
    }
    public static void changeMusic(){
        getPref().putInteger("music", 1-getPref().getInteger("music"));
        getPref().flush();
    }

    public static Score readScore(int indx){
        String[] stringScores = getPref().getString("leaderboard"+indx).split(", ");
        Integer[] score = new Integer[stringScores.length];
        int i = 0;
        for(String stringScore : stringScores){
            if(stringScore != "")
                score[i++] = Integer.parseInt(stringScore);
        }

        return new Score(score);
    }

    public static void addScore(int indx, int ponts){
        Score score = readScore(indx);
        score.addScore(ponts);
        String s="";
        for(Integer point : score.getScore()){
            s+=point+", ";
        }
        getPref().putString("leaderboard"+indx, s);
        getPref().flush();
    }
}
