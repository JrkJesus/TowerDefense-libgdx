package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Texture[] upgrades;
    private ControllerGame control;

    public Turret(com.towerdeffense.ControllerGame _control, Texture[] textures, int type, int x, int y ){
        super(textures[0]);
        control = _control;
        upgrades = textures;
        setPosition(x,y);
        level = 1;
        this.type = type;
        switch(type){
            case Constants.ANTIAIR:
//                radiusAttack = (float) (heigh * 0.25);
                radiusAttack = (int) (720/3.0f * Constants.ESCALA_Y);
                damage = 1;
                speedAttack = 2;
                initialCost = 75;
                upgradeCost = new int[]{50, 100};
                break;
            case Constants.ANTITANK:
//                radiusAttack = (float) (heigh * 0.14);
                radiusAttack = (int) (200 * Constants.ESCALA_Y);
                damage = 3;
                speedAttack = 3;
                initialCost = 100;
                upgradeCost = new int[]{75, 150};
                break;
            case Constants.MACHINEGUN:
//                radiusAttack = (float) (heigh * 0.18);
                radiusAttack = (int) (150* Constants.ESCALA_Y);
                damage = 1;
                speedAttack = 1;
                initialCost = 50;
                upgradeCost = new int[]{25, 75};
                break;
        }
    }

    public void levelUp(){
        if( level < 3) {
            setTexture(upgrades[level]);
            level++;
            radiusAttack += radiusAttack*0.15;
            speedAttack -= speedAttack*0.25;
            damage += (type == Constants.ANTITANK) ? 3 : 1;
        }
    }

    public void attack(){

        Array<Enemy> enemies = control.getEnemies();
        Enemy enemy = enemies.get(0);
        int i = 1,
                n = enemies.size;

        while( i < n && enemy.getType() % type != 0 )
            enemy = enemies.get(i++);

        if( i < n){
            enemy.loseLife(damage);
            reload = speedAttack;
        }

    }

    @Override
    public void draw(Batch batch) {
        if( reload < 0 ) attack();
        else reload -= Gdx.graphics.getDeltaTime();


        super.draw(batch);
    }

    public float x() {
        return getX()/Constants.ESCALA_X;
    }

    public float y(){
        return getY()/Constants.ESCALA_Y;
    }

    public int getValue() {
        int value = initialCost;
        for(int i = 0; i < level; i++){
            value += upgradeCost[i];
        }

        return (int) (value/3.5f);
    }

    public int getType(){
        return type;
    }
}
