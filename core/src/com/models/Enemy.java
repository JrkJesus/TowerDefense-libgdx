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

//TODO: 21/03/2017 ver si se hace extendiendo de Actor en vez de Sprite sigues teniendo
// los metodos getX, getY, setPosition, setRotation. Si lo es cambiarlo y cambiar el metodo draw. 
public class Enemy extends Sprite{

    private Vector2 velocity;
    private float speed;
    private int life, defense, startTime;
    private boolean fly, isAlive;
    private Array<Vector2> path;
    private int waypoint;
    
//    private Texture alive, death; //Si se puede hacer con Actor usar texture en vez de animation
    
// si se puede con actor añadir los campos texture alive y texture death y eliminar sprite.
    public Enemy(Sprite sprite, Array<Vector2> _path, int _starTime, int type){
        super(sprite);
        path = _path;
        isAlive = true;
        startTime = _starTime;
        
        switch(type){
            case Constants.PLANE:
                life = 4;
                speed = 5;
                defense = 0;
                fly = true;
            break;
            case Constants.TANK:
                life = 12;
                speed = 2.5;
                defense = 2;
                fly = false;
            break;
            case Constants.PEOPLE:
               life = 4;
                speed = 10;
                defense = 0;
                fly = false;
            break;
         }
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    /**
     *  Metodo para calcular los datos necesarios para donde se deberia pintar la siguiente 
     *  vez que llame a draw
     */
    private void update(float deltaTime) {
        float angle = (float) Math.atan2(path.get(waypoint).y - getY(), path.get(waypoint).x - getX()); //distance,
        velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

        setPosition(getX() + velocity.x * deltaTime, getY() + velocity.y * deltaTime);
        setRotation(angle * MathUtils.radiansToDegrees);
        //TODO: 21/03/2017 Confirmar si ha llegado al siguiente waypoint e incrementar la variable.
    }
    
    /**
     *  Metodo para calcular el daño entrante a la unidad. 
     *  Devuelve dicho daño.
     */
    private int reciveDamage(int dmg){
        int realDmg = dmg - defense;
        life -= realDmg;
        alive = life <= 0;
        
        return realDmg;
    }
}
