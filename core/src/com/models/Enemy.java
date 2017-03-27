package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.util.Constants;
import com.view.PantallaJuego;

import java.util.Iterator;

/**
 * Created by jesus on 16/03/2017.
 * 
 * Clase que recoge todo los datos sobre las unidades enemigas.
 */


public class Enemy extends Sprite {

    private Vector2 velocity, position, scala;
    private float speed;
    private int life, defense, startTime, deathTime;
    private boolean isAlive;
    private Array<Vector2> path;
    private int waypoint;
    private PantallaJuego player;
    
    public Enemy(PantallaJuego _player, Sprite sprite, Array<Vector2> _path, int _starTime, int type){
        super(sprite);
        player = _player;
        velocity = new Vector2();
        current = alive;
        current.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        path = _path;
        scala = new Vector2(player.width/20, player.height/11);
        position = new Vector2(path.get(0).x*scala.x, path.get(0).y*scala.y);
        waypoint = 1;
        isAlive = true;
        startTime = _starTime;
        switch(type){
            case Constants.PLANE:
                life = 4;
                speed = 50;
                defense = 6;
            break;
            case Constants.TANK:
                life = 12;
                speed = 25;
                defense = 2;
            break;
            case Constants.PEOPLE:
                life = 4;
                speed = 100;
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
//    public void draw(Batch batch, float alpha) {
    public void draw(SpriteBatch batch) {
        act(Gdx.graphics.getDeltaTime);
        if(startTime <= 0) {
//            batch.draw(current, position.x - current.getWidth() / 2, position.y - current.getHeight() / 2);
            super(batch);

            if(waypoint >= path.size) {
                player.loseLife(this);
                waypoint--;
            }
        }
    }

    /**
     *  Metodo para calcular los datos necesarios para donde se deberia pintar la siguiente 
     *  vez que llame a draw
     */
//    @Override
    public void act(float deltaTime) {
        
        if( !isAlive ){
            deathTime += deltaTime;
        } else {
            if( startTime > 0 ){
                startTime -= deltaTime;
            } else {
                float angle = (float) Math.atan2(path.get(waypoint).y*scala.y - position.y, path.get(waypoint).x*scala.x  - position.x);
                velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

                position.x += velocity.x * deltaTime;
                position.y += velocity.y * deltaTime;

                setRotation(angle * MathUtils.radiansToDegrees);

                if( Math.abs(position.x - path.get(waypoint).x*scala.x) <5  && Math.abs(position.y - path.get(waypoint).y*scala.y) < 5 ){
                    waypoint++;
                }
            }

        }
    }
    /**
     * Metodo para compropar si el actor se puede quitar del stage
     */
    public boolean isErasable(){
        return deathTime >= 300;
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

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Enemy))return false;
        Enemy otherMyClass = (Enemy)other;
        return otherMyClass.getPosition() == getPosition();
    }
}
