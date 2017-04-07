package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;
import com.towerdeffense.ControllerGame;
/**
 * Created by jesus on 16/03/2017.
 * <p>
 * Clase que guarda toda la información de las torretas.
 */

public class Turret extends Sprite {

    private int radiusAttack,
            initialCost,
            level,
            damage,
            type;
    private float speedAttack, reload;
    private int[] upgradeCost;
    private boolean isSelected;
    private Texture[] upgrades;
    private ControllerGame control;
    private Pixmap pixmap;
    private Texture circle;

    public Turret(com.towerdeffense.ControllerGame _control, Texture[] textures, int type, int x, int y ){
        super(textures[0]);
        setScale(Constants.ESCALA_X, Constants.ESCALA_Y);
        control = _control;
        upgrades = textures;
        setPosition(x,y);
        level = 1;
        this.type = type;
        switch(type){
            case Constants.ANTIAIR:
//                radiusAttack = (float) (heigh * 0.25);
                radiusAttack = (int) (720/3.0f * Constants.GRID_RESIZE_Y);
                damage = 1;
                speedAttack = 2;
                initialCost = 75;
                upgradeCost = new int[]{50, 100};
                break;
            case Constants.ANTITANK:
//                radiusAttack = (float) (heigh * 0.14);
                radiusAttack = (int) (200 * Constants.GRID_RESIZE_Y);
                damage = 3;
                speedAttack = 3;
                initialCost = 100;
                upgradeCost = new int[]{75, 150};
                break;
            case Constants.MACHINEGUN:
//                radiusAttack = (float) (heigh * 0.18);
                radiusAttack = (int) (150* Constants.GRID_RESIZE_Y);
                damage = 1;
                speedAttack = 1;
                initialCost = 50;
                upgradeCost = new int[]{25, 75};
                break;
        }
        isSelected = false;
        pixmap = new Pixmap(radiusAttack*2, radiusAttack*2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);
        circle = new Texture(pixmap);
    }

    public void levelUp(){
        if( level < 3) {
            setTexture(upgrades[level]);
            level++;
            radiusAttack += radiusAttack*0.15;
            speedAttack -= speedAttack*0.35;
            System.out.println(speedAttack);
            damage += (type == Constants.ANTITANK) ? 3 : 1;
            pixmap.dispose();
            circle.dispose();
            pixmap = new Pixmap(radiusAttack*2, radiusAttack*2, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);
            circle = new Texture(pixmap);
        }
    }

    public void attack(){
        // TODO: 06/04/2017 Comprobar el caso de que solo haya un enemigo
        Array<Enemy> enemies = control.getEnemies();
        Enemy enemy = enemies.get(0);
        int i = 1,
                n = enemies.size;

        while( i < n && ! isReachable(enemy) )
            enemy = enemies.get(i++);

        if( i < n || (n == 1 && isReachable(enemy))){
            enemy.loseLife(damage);
            reload = speedAttack;
            this.setRotation((float)(Math.atan2(enemy.y() - y(), enemy.x() - x())) * MathUtils.radiansToDegrees - 90);
        }

    }

    private boolean isReachable(Enemy enemy) {

        return enemy.getDeathTime() == 0
                && enemy.getType() % type == 0
                && Math.sqrt((enemy.getX() - getX())*(enemy.getX() - getX()) + (enemy.getY() - getY())*(enemy.getY() - getY()) ) < radiusAttack;
    }

    @Override
    public void draw(Batch batch) {
        if( reload < 0 ) attack();
        else reload -= Gdx.graphics.getDeltaTime();

        if(isSelected){
            batch.draw(circle, getX() - radiusAttack + Constants.GRID_RESIZE_X/2, getY() - radiusAttack + Constants.GRID_RESIZE_X/2);
        }

        super.draw(batch);
    }

    public float x() {
        return getX()/Constants.GRID_RESIZE_X;
    }

    public float y(){
        return getY()/Constants.GRID_RESIZE_Y;
    }

    public int getValue() {
        int value = initialCost,
            max = level < 3 ? level : 2;

        for(int i = 0; i < max; i++){
            value += upgradeCost[i];
        }

        return (int) (value/3.5f);
    }

    public void dispose(){
        circle.dispose();
        pixmap.dispose();
    }

    public void select(){
        isSelected = true;
    }

    public int getType(){
        return type;
    }

    public void unselect() { isSelected = false; }

    public int getUpgradeCost() {
        return level < 3 ? upgradeCost[level-1] : Integer.MAX_VALUE;
    }
}
