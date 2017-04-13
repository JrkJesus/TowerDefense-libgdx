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
    private int x, y;

    public Tile(Texture texture, int gridX, int gridY, boolean road) {
        super(texture);
        setPosition(gridX * Constants.GRID_RESIZE_X, gridY * Constants.GRID_RESIZE_Y);
        setScale(Constants.ESCALA_X, Constants.ESCALA_Y);
        this.road = road;
        x = gridX;
        y = gridY;
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        if (degrees == 90 || degrees == 270) {
            setScale(Constants.ESCALA_Y, Constants.ESCALA_X);
        }

    }

    public boolean isBuildeable() {
        return !road;
    }

    public boolean isUpgreadable() {
        return turret != null;
    }

    public void buildTurret(Texture[] t, int type) {
        turret = new Turret(t, type, (int) getX(), (int) getY());
    }

    public void update(ControllerGame control) {
        if (turret != null )
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

    public void lvlUp(ControllerGame control) {
        if (turret.getLvl() < 3) {
            control.addMoney(-turret.lvlUp());
        }
    }

    public void sell(ControllerGame control) {
        control.addMoney(turret.getValue());
        turret = null;
    }

    public int upgradeCost() {
        return turret.getUpgradeCost();
    }

}
