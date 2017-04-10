package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.towerdeffense.ControllerGame;
import com.util.Constants;

/**
 * Created by jesus on 16/03/2017.
 * <p>
 * Clase que recoge los datos sobre las unidades enemigas.
 */

public class Enemy extends Sprite {

    private Texture dead;
    private int tipo,
            life,
            movSpeed,
            valor,
            waypoint;
    private float deadTime;
    private Array<Vector2> path;
    private Vector2 speed;


    //FUNCTIONS
    public Enemy(Texture[] t, int type, Array<Vector2> path, int initialPos) {
        super(t[0]);
        setPosition(-initialPos, path.get(0).y*Constants.GRID_RESIZE_Y);
        setScale(Constants.ESCALA_X,Constants.ESCALA_Y);
        speed = new Vector2();
        dead = t[1];
        switch (type) {
            case Constants.PLANE:
                life = 3;
                movSpeed = 50;
                valor = 10;
                break;
            case Constants.PEOPLE:
                life = 3;
                movSpeed = 75;
                valor = 5;
                break;
            case Constants.TANK:
                life = 9;
                movSpeed = 40;
                valor = 10;
                break;
        }
        this.path = path;
    }

    public void dispose() {
        getTexture().dispose();
        dead.dispose();
    }

    public void nextStep(float deltaTime) {
        float angle = (float) Math.atan2(path.get(waypoint).y * Constants.GRID_RESIZE_Y + Constants.GRID_RESIZE_Y / 2 - getTexture().getWidth()/2 - getY(),
                                         path.get(waypoint).x * Constants.GRID_RESIZE_X + Constants.GRID_RESIZE_X / 2 - getTexture().getHeight()/2 - getX());
        speed.set(movSpeed * (float) Math.cos(angle), movSpeed * (float) Math.sin(angle));
        this.setRotation(angle*MathUtils.radiansToDegrees);
        this.setPosition(getX() + speed.x * deltaTime, getY() + speed.y * deltaTime);

        if (waypoint < path.size - 1 && wayPointReached()) {
            waypoint++;
        }
    }

    private boolean wayPointReached() {
        int tolerancia = (int) (Constants.GRID_RESIZE_X * 0.2);
        return Math.abs(getX() - path.get(waypoint).x*Constants.GRID_RESIZE_X + Constants.GRID_RESIZE_X / 2 - getTexture().getWidth()/2) < tolerancia
                && Math.abs(getY() - path.get(waypoint).y*Constants.GRID_RESIZE_Y + Constants.GRID_RESIZE_Y / 2 - getTexture().getHeight()/2) < tolerancia;
    }

    @Override
    public void draw(Batch batch) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (life > 0) {
            nextStep(deltaTime);
        } else {
            deadTime += deltaTime;
        }
        super.draw(batch);
    }

    public void receiveDamage(ControllerGame control,int dmg) {
        this.life -= dmg;
        if (life <= 0) {
            this.setTexture(dead);
            control.addMoney(valor);
            control.addScore(valor);
        }
    }

    public int x(){
        return (int)getX()/ (int)Constants.GRID_RESIZE_X;
    }

    public int y(){
        return (int)getY()/ (int)Constants.GRID_RESIZE_Y;
    }

    public float getDeadTime() {
        return deadTime;
    }

    public int getType() {
        return tipo;
    }
}
