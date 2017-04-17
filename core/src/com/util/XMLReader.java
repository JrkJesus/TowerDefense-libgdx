package com.util;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


/**
 * Created by jesus on 16/04/2017.
 */

public class XMLReader {

    public static Config readConfig(){
        FileHandle file = Gdx.files.local("Configuracion/configuration.json");
        String config = file.readString();
        Json json = new Json();
        return json.fromJson(Config.class, config);
    }

    public static void writeConfiguration(Config config){
        Json  json = new Json();
        String configuration = json.prettyPrint(config);
        FileHandle handle = Gdx.files.local("Configuracion/configuration.json");
        handle.writeString(configuration, false);

    }

    public static void changeDificulty(int dificulty){
        Config config = readConfig();
        config.dificultad = dificulty;
        writeConfiguration(config);
    }
    public static void changeSound(){
        Config config = readConfig();
        config.sound = 1 - config.sound;
        writeConfiguration(config);
    }
    public static void changeMusic(){
        Config config = readConfig();
        config.music = 1 - config.music;
        writeConfiguration(config);
    }

    public static Score readScore(int indx){
        FileHandle file = Gdx.files.local("Configuracion/score"+indx+".json");
        String score = file.readString();
        Json json = new Json();
        return json.fromJson(Score.class, score);
    }

    public static void addScore(int indx, int ponts){
        Score score = readScore(indx);
        score.addScore(ponts);
        Json  json = new Json();
        String leaderboard = json.prettyPrint(score);
        FileHandle handle = Gdx.files.local("Configuracion/score"+indx+".json");
        handle.writeString(leaderboard, false);
    }

    public static void writeScore(int indx){
        Score score = new Score(8);
        Json  json = new Json();
        String leaderboard = json.prettyPrint(score);
        FileHandle handle = Gdx.files.local("Configuracion/score"+indx+".json");
        handle.writeString(leaderboard, false);
    }

}
