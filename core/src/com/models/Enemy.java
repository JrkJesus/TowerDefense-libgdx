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
 * 
 * Clase que recoge los datos sobre las unidades enemigas.
 */

public class Enemy extends Sprite {
    private int life,
            speed,
            waypoint,
            type;
    private Texture death;
    private Array<Vector2> path;
    private Vector2 velocity;
    private float deathTime;
    private ControllerGame control;


    public Enemy(ControllerGame controllerGame, Texture[] textures, int type, Array<Vector2> _path, int startPosition){
        super(textures[0]);
        setScale(Constants.ESCALA_X, Constants.ESCALA_Y);
        control = controllerGame;
        death = textures[1];
        setPosition(-startPosition , _path.get(0).y*Constants.GRID_RESIZE_Y);
        velocity = new Vector2();
        this.type = type;
        switch (type){
            case Constants.PEOPLE:
                life = 3;
                speed = 75;
                break;
            case Constants.TANK:
                life = 9;
                speed = 40;
                break;
            case Constants.PLANE:
                life = 3;
                speed = 50;
                break;
        }
        deathTime = 0;
        waypoint = 0;
        path = _path;
    }

    private void walk(float deltaTime){
        float angle = (float) Math.atan2(path.get(waypoint).y*Constants.GRID_RESIZE_Y - getY(),
                path.get(waypoint).x*Constants.GRID_RESIZE_X  - getX());
        velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

        float x = getX() + velocity.x * deltaTime,
                y =  getY() + velocity.y * deltaTime;
        setPosition( x , y );

        setRotation(angle * MathUtils.radiansToDegrees);

        if(isReachWaypoint() && waypoint < path.size-1){
            waypoint++;
        }
    }

    private boolean isReachWaypoint(){
        int tolerance = 5;
        return Math.abs(getX() - path.get(waypoint).x * Constants.GRID_RESIZE_X) < tolerance &&
                Math.abs(getY() - path.get(waypoint).y * Constants.GRID_RESIZE_Y) < tolerance;

    }

    public void loseLife(int amount){
        life -= amount;
        if ( life <= 0 ){
            this.setTexture(death);
            control.addMoney(type*10);
            control.addScore(type*10);
        }
    }

    @Override
    public void draw(Batch batch) {
        float delta = Gdx.graphics.getDeltaTime();

        if (life > 0) walk(delta);
        else deathTime += delta;

        super.draw(batch);
    }

    public int getType(){
        return type;
    }

    public float getDeathTime(){
        return deathTime;
    }

    public int x(){
        return (int) (getX() / Constants.GRID_RESIZE_X);
    }

    public int y(){
        return (int) (getY() / Constants.GRID_RESIZE_Y);
    }

}
