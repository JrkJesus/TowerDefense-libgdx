package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.models.Map;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.util.Constants;

/**
 * Created by jesus on 09/04/2017.
 */

public class Map {

    private Tile[] map;
    private RandomXS128 rnd = new RandomXS128();

    public Map(){
        int heigh = Constants.GRID_HEIGH, width = Constants.GRID_WIDTH;
        map = Constants.GENERATEMAP();
    }


    public void draw(Batch batch){
        for(Tile tile : map){
            tile.draw(batch);
        }

        if(Gdx.input.justTouched() )
    }

    public void dispose(){
        for(Tile tile : map){
            tile.dispose();
        }
    }



}
