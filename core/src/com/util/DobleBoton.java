package com.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jesus on 15/04/2017.
 */

public class DobleBoton extends BotonesMenu {

    Texture otherTexture;

    public DobleBoton(Texture noPulsado, Texture pulsado, Vector2 position) {
        super(noPulsado, position);
        otherTexture = pulsado;
    }

    public void draw(Batch batch, boolean option) {
        if(option)
            super.draw(batch);
        else
            batch.draw(otherTexture, position.x, position.y);
    }

    @Override
    public void dispose() {
        super.dispose();
        otherTexture.dispose();
    }
}
