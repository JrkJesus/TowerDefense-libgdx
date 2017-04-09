package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

    public Tile(int texture, int gridX, int gridY){
        super();
        setPosition(gridX*Constants.GRID_RESIZE_X, gridY*Constants.GRID_RESIZE_Y);
        road = true;
        switch (texture){
            case Constants.UP:
                setTexture(new Texture(Gdx.files.internal("Path\\up.png")));
                break;
            case Constants.LEFT:
                setTexture(new Texture(Gdx.files.internal("Path\\left.png")));
                break;
            case Constants.GIRO:
                setTexture(new Texture(Gdx.files.internal("Path\\giro.png")));
                break;
            default:
                setTexture(new Texture(Gdx.files.internal("Path\\ground.png")));
                road = false;
                break;
        }
    }

    public boolean isBuildeable(){
        return road && turret == null;
    }

    @Override
    public void draw(Batch batch){
        super.draw(batch);
    }

    public void dispose(){
        getTexture().dispose();
    }

}
