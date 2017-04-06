package com.Limpieza;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;

/**
 * Created by jesus on 05/04/2017.
 */

public class Enemy extends Sprite {

    private int life,
            speed,
            defense,
            waypoint;
    private Texture death;
    private Array<Vector2> path;
    private Vector2 velocity;
    private float deathTime;


    public Enemy(Texture[] textures, int type, Array<Vector2> _path, int startPosition){

        super(textures[0]);
        setPosition(-startPosition, _path.get(0).y);
        velocity = new Vector2();
        switch (type){
            case Constants.PEOPLE:
                life = 4;
                speed = 75;
                defense = 0;
                break;
            case Constants.TANK:
                life = 12;
                speed = 40;
                defense = 2;
                break;
            case Constants.PLANE:
                life = 4;
                speed = 50;
                defense = 6;
                break;
        }
        deathTime = 0;
        waypoint = 0;
        path = _path;
    }

    private void walk(float deltaTime){
        float angle = (float) Math.atan2(path.get(waypoint).y*Constants.ESCALA_Y - getY(), path.get(waypoint).x*Constants.ESCALA_X  - getX());
        velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

        setPosition( velocity.x * deltaTime - getWidth()/2,  velocity.y * deltaTime - getHeight()/2);

        setRotation(angle * MathUtils.radiansToDegrees);

        if(isReachWaypoint()){
            waypoint++;
        }
    }

    private boolean isReachWaypoint(){
        int tolerance = 5;
        return Math.abs(getX() - path.get(waypoint).x * Constants.ESCALA_X) < tolerance &&
            Math.abs(getY() - path.get(waypoint).y * Constants.ESCALA_Y) < tolerance;

    }

    public int loseLife(int amount){
        life -= amount - defense;
        if ( life < 0 ) this.setTexture(death);
        return life;
    }

    @Override
    public void draw(Batch batch) {
        float delta = Gdx.graphics.getDeltaTime();

        if (life > 0) walk(delta);
        else deathTime += delta;

        super.draw(batch);
    }

    public float getDeathTime(){
        return deathTime;
    }
}
