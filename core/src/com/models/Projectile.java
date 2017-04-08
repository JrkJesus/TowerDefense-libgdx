package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.util.Constants;

/**
 * Created by Antonio Ruz on 07/04/2017.
 */
public class Projectile extends Sprite{

    Vector2 target, velocity;
    float angle, speed = 150;

    public Projectile(int type, Vector2 initial, Vector2 target){
        super();
        switch (type){
            case Constants.ANTIAIR:
                setTexture(new Texture(Gdx.files.internal("Textures\\missile.png")));
                break;
            case Constants.ANTITANK:
                setTexture(new Texture(Gdx.files.internal("Textures\\bomb.png")));
                break;
            case Constants.MACHINEGUN:
                Pixmap pixmap = new Pixmap((int) (50*Constants.ESCALA_X), (int)(50*Constants.ESCALA_Y), Pixmap.Format.RGBA8888);
                pixmap.drawLine(pixmap.getWidth()/2, 0, pixmap.getWidth()/2, pixmap.getHeight());
                setTexture(new Texture(pixmap));
                pixmap.dispose();
                break;
        }
        setPosition(initial.x, initial.y);
        this.target = target;
        angle = (int) Math.atan2(target.y - initial.y, target.x - initial.x) * MathUtils.radiansToDegrees;
        setRotation( angle );
        velocity = new Vector2();
    }

    private void walk(float deltaTime){
        velocity.set((float) Math.cos(angle)*speed, (float) Math.sin(angle)*speed);

        float x = getX() + + velocity.x * deltaTime,
                y =  getY() + velocity.y * deltaTime;
        System.out.println(x + ", " + y);
        setPosition( x , y );
    }

    private boolean impact(){       
        int tolerance = 5;
        return Math.abs(getX() - target.x) < tolerance &&
                Math.abs(getY() - target.y ) < tolerance;

    }

    @Override
    public void draw(Batch batch) {
//        if(!impact()) {
            walk(Gdx.graphics.getDeltaTime());
            super.draw(batch);
//        }
    }
}
