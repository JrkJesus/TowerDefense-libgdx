package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antonio Ruz on 07/04/2017.
 */
public class Projectile extends Sprite {

    private Vector2 target,
            speed;
    private float angle, movSpeed;
    private boolean hundido;

    public Projectile(Texture t, Vector2 initialPos, Vector2 target) {
        super(t);
        setPosition(initialPos.x, initialPos.y);
        this.target = target;
        angle = (float) (Math.atan2(target.y - initialPos.y, target.x - initialPos.x) * MathUtils.radiansToDegrees);
        setRotation(angle);
        hundido = false;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void nextStep(float deltaTime) {
        speed.set(movSpeed * (float) Math.cos(angle), movSpeed * (float) Math.sin(angle));
        setRotation(angle * MathUtils.degreesToRadians);
        setPosition(getX() + speed.x * deltaTime, getY() + speed.y * deltaTime);
        if (Math.abs(getX() - target.x) < 0 && Math.abs(getX() - target.x) < 0) {
            hundido = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (!hundido) {
            nextStep(deltaTime);
            super.draw(batch);
        }
    }

    public void dispose(){
        getTexture().dispose();
    }
}
