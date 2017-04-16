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
    private float delay;
    private double recarga;

    public Projectile(Texture t, Vector2 initialPos, Vector2 target, int velocidad) {
        super(t);
        setPosition(initialPos.x - getWidth() / 2, initialPos.y - getHeight() / 2);
        this.target = new Vector2(target.x - getWidth() / 2, target.y - getHeight() / 2);
        angle = (float) (Math.atan2(target.y - initialPos.y, target.x - initialPos.x));
        setRotation(angle * MathUtils.radiansToDegrees);
        hundido = false;
        speed = new Vector2();
        movSpeed = 100 * velocidad;

        delay = velocidad/4;

        recarga = 0;
    }

    public boolean isHundido() {
        System.out.println("recarga:"+recarga);
        System.out.println("delay:"+delay);
        return hundido && recarga >= delay;

    }

    public void nextStep(float deltaTime) {
        speed.set(movSpeed * (float) Math.cos(angle), movSpeed * (float) Math.sin(angle));
        setPosition(getX() + speed.x * deltaTime, getY() + speed.y * deltaTime);
        if (Math.abs(getX() - target.x) < getTexture().getWidth() / 2 && Math.abs(getX() - target.x) < getTexture().getHeight() / 2) {
            hundido = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        recarga += deltaTime;
        if (!hundido) {
            nextStep(deltaTime);
            super.draw(batch);
        }
    }

    public void dispose() {
        getTexture().dispose();
    }
}
