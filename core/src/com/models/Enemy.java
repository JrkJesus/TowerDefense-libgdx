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
 */

public class Enemy extends Sprite{

    protected Vector2 velocity;
    protected float speed;
    private Array<Vector2> path;
    private int waypoint;

    public Enemy(Sprite sprite, Array<Vector2> _path){
        super(sprite);
        path = _path;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    private void update(float deltaTime) {
        float angle = (float) Math.atan2(path.get(waypoint).y - getY(), path.get(waypoint).x - getX()); //distance,
        velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

        setPosition(getX() + velocity.x * deltaTime, getY() + velocity.y * deltaTime);
        setRotation(angle * MathUtils.radiansToDegrees);
    }
}
