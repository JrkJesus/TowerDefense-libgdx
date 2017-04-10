package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by jesus on 09/04/2017.
 */

public class Tile extends Sprite {

    private boolean road;
    private Turret turret;
    private int i;
    private static int n = 0;

    public Tile(Texture texture, int gridX, int gridY, boolean road){
        super(texture);
        setPosition(gridX*Constants.GRID_RESIZE_X, gridY*Constants.GRID_RESIZE_Y);
        this.road = road;
        i = n++;
    }

    public boolean isBuildeable(){
        return road && turret == null;
    }

    @Override
    public void draw(Batch batch){
        super.draw(batch);
        batch.draw(getTexture(), getVertices(), 0, getVertices().length);
        BitmapFont font = new BitmapFont(Gdx.files.internal("GUI\\font-export.fnt"), Gdx.files.internal("GUI\\font-export.png"), false);
    }

    public void dispose(){
        getTexture().dispose();
    }

}
