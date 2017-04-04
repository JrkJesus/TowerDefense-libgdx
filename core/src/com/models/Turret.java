package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.util.Constants;

/**
 * Created by jesus on 16/03/2017.
 * <p>
 * Clase que guarda toda la información de las torretas.
 */

public class Turret extends Sprite {

    private float radiusAttack, speedAttack, reload;
    private int dmg, initialCost, level;
    private int[] upgradeCost;
    private boolean reachFlying, optionsReveal = false;
    private Vector2 position, enemy;
    private Texture[] turret;
    private ShapeRenderer sr;

    public Turret(Texture[] upgrade, int type, int x, int y) {
        super(upgrade[0]);
        setPosition(x, y);
        sr = new ShapeRenderer();
        position = new Vector2(x/64, y/64);
        enemy = position;
        turret = upgrade;
        level = 1;
        int heigh = Gdx.graphics.getHeight();
        switch (type) {
            case Constants.ANTIAIR:
                radiusAttack = (float) (heigh * 0.25);
                dmg = 7;
                speedAttack = 2;
                initialCost = 75;
                upgradeCost = new int[]{50, 100};
                break;
            case Constants.ANTITANK:
                radiusAttack = (float) (heigh * 0.14);
                dmg = 6;
                speedAttack = 3;
                initialCost = 100;
                upgradeCost = new int[]{75, 150};
                break;
            case Constants.MACHINEGUN:
                radiusAttack = (float) (heigh * 0.18);
                dmg = 100; // TODO: 04/04/2017 Cambiar daño (esta para testear) a 2
                speedAttack = 1;
                initialCost = 50;
                upgradeCost = new int[]{25, 75};
                break;
        }
    }

    /**
     * Metodo
     */
    public int getAttack() {
        return dmg;
    }

    /**
     * Metodo
     */
    public void attack(int x, int y) {
        position.set(x,y);
        reload = speedAttack;
        double angle = Math.atan2(getY() - y*64, getX() - x*64)+90;
        this.setRotation((float) (MathUtils.radiansToDegrees*angle));
    }
// TODO: 04/04/2017 overide equals


//    @Override
//    public boolean equals(Object o) {
//        if(o == null) return false;
//        else if ( ! (o instanceof Turret) ) return false;
//        else{
//            Turret turret = (Turret) o;
//            return turret.getX() == getX() && turret.getY() == getY();
//        }
//    }

    /**
     * Metodo para comprobar si un enemigo esta al alcance
     */
    public boolean isReachable(Vector2 enemy) {
        System.out.println(radiusAttack);
        System.out.println(Math.sqrt((enemy.x - position.x) * (enemy.x - position.x) + (enemy.y - position.y) * (enemy.y - position.y)));
        System.out.println(Math.sqrt((enemy.x - position.x) * (enemy.x - position.x) + (enemy.y - position.y) * (enemy.y - position.y)) <= radiusAttack);
        return Math.sqrt((enemy.x - position.x) * (enemy.x - position.x) + (enemy.y - position.y) * (enemy.y - position.y)) <= radiusAttack;
    }

    /**
     * Metodo para comprobar si un enemigo esta al alcance
     */
    public boolean isReloading() {
        return reload > 0;
    }

    /**
     * Metodo para upgradear una torreta
     */
    public void levelUp() {
        if (level < 3) { // si lo controlamos desde el panel se puede obviar.
            radiusAttack += radiusAttack * 0.1;
            speedAttack -= speedAttack * 0.05;
            dmg += dmg * 0.1 > 0 ? dmg * 0.1 : 1;
            setTexture(turret[level]);
            level++;
        }
    }

    @Override
    public void draw(Batch batch) {
        act(Gdx.graphics.getDeltaTime());
        super.draw(batch);
//        sr.begin(ShapeRenderer.ShapeType.Line);
//        sr.line(getX()+getWidth()/2, getY()+getHeight()/2, (enemy.x)*64+32, (enemy.y)*64+32);
//        sr.end();
    }

    public void act(float deltaTime) {
        if (reload > 0) {
            reload -= deltaTime;
        }
    }


    public double getValue() {
        return initialCost;
    }

    public boolean position(int x, int y){
        return x == position.x && y == position.y;
    }
}
