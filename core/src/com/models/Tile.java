package com.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.towerdeffense.ControllerGame;
import com.util.Constants;

/**
 * Created by jesus on 09/04/2017.
 */

public class Tile extends Sprite {

    private boolean road;
    private Turret turret;

    public Tile(Texture texture, int gridX, int gridY, boolean road) {
        super(texture);
        setPosition(gridX * Constants.GRID_RESIZE_X, gridY * Constants.GRID_SIZE);
        System.out.println("GetX:"+getX()+"   GetY"+getY());
        this.road = road;
    }

    public boolean isBuildeable() {
        return road && turret == null;
    }

    public void buildTurret(Texture[] t, int type, ControllerGame control){
        turret=new Turret(t,type,(int)getX(),(int)getY());
    }

    public void update(ControllerGame control) {
        if(turret!=null && turret.shootable())
            turret.shoot(control);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (turret != null)
            turret.draw(batch);
    }

    public void dispose() {
        getTexture().dispose();
        if (turret != null)
            turret.dispose();
    }

    public void lvlUp(ControllerGame control){
        if(turret.getLvl()<3) {
            control.addMoney(-turret.lvlUp());
        }
    }

    public void sell(ControllerGame control){
        control.addMoney(turret.getValue());
        turret.dispose();
        turret=null;
    }

}
