package com.Limpieza;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.models.*;
import com.util.Constants;

/**
 * Created by jesus on 05/04/2017.
 */

public class Turret extends Sprite {

    private int radiusAttack, initialCost, level, damage;
    private float speedAttack, reload;
    private int[] upgradeCost;
    private Texture[] upgrades;
    private ControllerGame control;

    public Turret(Texture[] textures, int type, int x, int y ){
        super(textures[0]);
        upgrades = textures;
        setPosition(x,y);
        level = 1;

        switch(type){
            case Constants.ANTIAIR:
//                radiusAttack = (float) (heigh * 0.25);
                radiusAttack = (int) (720/3.0f * Constants.ESCALA_Y);
                damage = 7;
                speedAttack = 2;
                initialCost = 75;
                upgradeCost = new int[]{50, 100};
                break;
            case Constants.ANTITANK:
//                radiusAttack = (float) (heigh * 0.14);
                radiusAttack = (int) (250 * Constants.ESCALA_Y);
                damage = 6;
                speedAttack = 3;
                initialCost = 100;
                upgradeCost = new int[]{75, 150};
                break;
            case Constants.MACHINEGUN:
//                radiusAttack = (float) (heigh * 0.18);
                radiusAttack = (int) (175* Constants.ESCALA_Y);
                damage = 2;
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
            radiusAttack += radiusAttack*0.1;
            speedAttack -= speedAttack*0.25;
            // TODO: 05/04/2017 comprobar el aumento de daño las repercusiones que tendria con la armadura
        }
    }

    public void attack(){
        if( reload < 0 ){
            Array<Enemy> enemies = control.getEnemies();
            int i = 0;
            while( enemies.get(i++).loseLife(damage) < 0 );
            reload = speedAttack;
        } else {
            reload -= Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void draw(Batch batch) {
        attack();
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
}
