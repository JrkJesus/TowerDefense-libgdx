package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by jesus on 16/03/2017.
 * 
 * Clase que recoge todo los datos sobre las unidades enemigas.
 */


public class Enemy extends Actor{

    private Vector2 velocity, position;
    private float speed;
    private int life, defense, startTime, deathTime;
    private boolean isAlive;
    private Array<Vector2> path;
    private int waypoint;
    private Texture alive, death, current;
    
    public Enemy(Texture[] textures, Array<Vector2> _path, int _starTime, int type){
        alive = textures.get(0);
        death = textures.get(1);
        current = alive;
        path = _path;
        position = path.get(0);
        waypoint = 1;
        isAlive = true;
        startTime = _starTime;
        
        switch(type){
            case Constants.PLANE:
                life = 4;
                speed = 20;
                defense = 6;
            break;
            case Constants.TANK:
                life = 12;
                speed = 10;
                defense = 2;
            break;
            case Constants.PEOPLE:
               life = 4;
                speed = 40;
                defense = 0;
            break;
         }
    }
    
    /**
     * Metodo 
     */
    public boolean isAttackable(){
        return isAlive;
    }
    
    /**
     * Metodo 
     */
    public Vector2 getPosition(){
        return position;
    }  
    
    /**
     * Metodo 
     */
    @Override
    public void draw(Batch batch, float alpha) {
        if( starTime > 0 {
            batch.draw(current, position.x, position.y);
        }
    }

    /**
     *  Metodo para calcular los datos necesarios para donde se deberia pintar la siguiente 
     *  vez que llame a draw
     */
    @Override
    public void act(float deltaTime) {
        
        if( !isAlive ){
            deathTime += deltaTime;
        } else {
            if( starTime > 0 ){
                starTime -= deltaTime;
            } else {
                float angle = (float) Math.atan2(path.get(waypoint).y - position.y, path.get(waypoint).x - position.x);
                velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

                position.x =+ velocity.x * deltaTime;
                position.y =+ velocity.y * deltaTime

                //TODO: 21/03/2017 comprobar si el enemigo rota con esta funcion.
                setRotation(angle * MathUtils.radiansToDegrees);

                if( position.x == path.get(waypoint).x && position.y = path.get(waypoint).y ){
                    waypoint++;
                }
            }
        }
    }
    
    /**
     * Metodo para compropar si el actor se puede quitar del stage
     */
    public boolean isErasable(){
        return deathTime >= 3000;
    }
    
    /**
     *  Metodo para calcular el daño entrante a la unidad. 
     *  Devuelve dicho daño.
     */
    public int reciveDamage(int dmg){
        int realDmg = dmg - defense;
        life -= realDmg>0 ? realDmg : 0;
        if( life <= 0 ){
            isAlive = false;
            current = death;
        }
        
        return realDmg;
    }
}
