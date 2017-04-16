package com.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antonio on 14/04/2017.
 */

public class BotonesMenu {

    protected Texture noPulsado;
    protected Vector2 position;

    public BotonesMenu(Texture noPulsado, Vector2 position) {
        this.noPulsado = noPulsado;
        this.position = new Vector2(position.x - noPulsado.getWidth() / 2, position.y - noPulsado.getHeight() / 2);
    }

    public void draw(Batch batch) {
        batch.draw(noPulsado, position.x, position.y);
    }

    public boolean isSeleccionado(int x, int y) {
        return ((x > position.x && x < position.x + noPulsado.getWidth()) && (y > position.y && y < position.y + noPulsado.getHeight()));
    }

    public void dispose() {
        noPulsado.dispose();
    }

}
