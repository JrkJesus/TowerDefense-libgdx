package com.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.util.Constants;

/**
 * Created by jesus on 09/04/2017.
 */

public class Map {

    private Tile[] map;
    private RandomXS128 rnd = new RandomXS128();

    public Map(){
        map = Constants.GENERATEMAP();
    }


    public void draw(Batch batch){
        for(Tile tile : map){
            tile.draw(batch);
        }
    }

    public void dispose(){
        for(Tile tile : map){
            tile.dispose();
        }
    }



}
