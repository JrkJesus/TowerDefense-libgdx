package com.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antonio on 14/04/2017.
 */

public class BotonesMenu {

    private Texture noPulsado;
    private Vector2 position;
    private boolean seleccionado;


    public BotonesMenu(Texture noPulsado, Vector2 position) {
        this.noPulsado = noPulsado;
        this.position = position;
        this.seleccionado = false;
    }

    public void draw(Batch batch) {
        batch.draw(noPulsado, position.x, position.y);
    }

    private boolean isSeleccionado(int x, int y) {
        return ((x > position.x && x < position.x + noPulsado.getWidth()) && (y > position.y && y < position.y + noPulsado.getHeight()));
    }



}
