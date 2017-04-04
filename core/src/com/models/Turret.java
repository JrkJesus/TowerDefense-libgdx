package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

public class Turret extends Actor {

    private float radiusAttack, speedAttack, reload;
    private int dmg, initialCost, level;
    private int[] upgradeCost;
    private boolean reachFlying, optionsReveal = false;
    private Vector2 position;
    private Texture currentTurret;
    private Texture[] turret;

    public Turret(Texture[] _turret, int type, int x, int y) {

        position = new Vector2(x, y);
        //TODO: 21/03/2017 Ver si con el listener puede ejecutar alguna funcion desde el stage (player)
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((Turret) event.getTarget()).optionsReveal = !((Turret) event.getTarget()).optionsReveal;
                return true;
            }
        });
        turret = _turret;
        currentTurret = turret[0];
        setBounds(x, y, currentTurret.getWidth(), currentTurret.getHeight());
        level = 1;
        int heigh = Gdx.graphics.getHeight();
        switch (type) {
            case Constants.ANTIAIR:
                radiusAttack = (float) (heigh * 0.25);
                dmg = 7;
                speedAttack = 1000;
                initialCost = 75;
                upgradeCost = new int[]{50, 100};
                break;
            case Constants.ANTITANK:
                radiusAttack = (float) (heigh * 0.14);
                dmg = 6;
                speedAttack = 2000;
                initialCost = 100;
                upgradeCost = new int[]{75, 150};
                break;
            case Constants.MACHINEGUN:
                radiusAttack = (float) (heigh * 0.18);
                dmg = 2;
                speedAttack = 500;
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
    public void reload() {
        reload = speedAttack;
    }

    /**
     * Metodo para comprobar si un enemigo esta al alcance
     */
    public boolean isReachable(Vector2 enemy) {
        return Math.sqrt((enemy.x - position.x) * (enemy.x - position.x) + (enemy.y - position.y) * (enemy.y - position.y)) < radiusAttack;
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
            currentTurret = turret[level];
            level++;
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(currentTurret, position.x, position.y);
        if (optionsReveal) {
            //TODO: 21/03/2017 Añadir los botones de acciones con sus propias opciones.
        }
    }

    @Override
    public void act(float deltaTime) {
        if (reload > 0) {
            reload -= reload - deltaTime < 0 ? 0 : reload - deltaTime;
        }
    }


    public double getValue() {
        return initialCost;
    }
}
