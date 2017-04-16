package com.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antonio on 14/04/2017.
 */

public class Multibotones extends BotonesMenu {
    private BotonesMenu[] botones;
    private Texture pressed;
    private boolean isPress;

    public Multibotones(Texture noPulsado, Vector2 position) {
        super(noPulsado, position);
    }

    public Multibotones() {
        this(new Texture(Gdx.files.internal("GUI\\setting.png")), new Vector2(74, Gdx.graphics.getHeight() - 74));
        pressed = new Texture(Gdx.files.internal("GUI\\setting-selected.png"));
        int x = 110,
                y = Gdx.graphics.getHeight() - pressed.getHeight() - 10;

        botones = new BotonesMenu[]{
                new BotonesMenu(new Texture(Gdx.files.internal("GUI\\leaderboard.png")), new Vector2(x + 10, y)),
                new DobleBoton(new Texture(Gdx.files.internal("GUI\\sound-high.png")), new Texture(Gdx.files.internal("GUI\\sound-low.png")), new Vector2(x + 69, y )),
                new DobleBoton(new Texture(Gdx.files.internal("GUI\\musicOn.png")), new Texture(Gdx.files.internal("GUI\\musicOff.png")), new Vector2(x + 64*2+5, y ))
        };
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        Tuple<Integer, Integer, Boolean> setting = XMLReader.getConfiguration();
        if (isPress) {
//            for (BotonesMenu boton : botones) {
//                boton.draw(batch);
//            }
            botones[0].draw(batch);
            ((DobleBoton)botones[1]).draw(batch, setting.item2 == 1);
            ((DobleBoton)botones[2]).draw(batch, setting.item3);
        }
    }

    @Override
    public boolean isSeleccionado(int x, int y) {
        boolean press = super.isSeleccionado(x, y);
        if (press) {
            Texture temp = pressed;
            pressed = noPulsado;
            noPulsado = temp;
            isPress = !isPress;
        }

        return press;
    }

    @Override
    public void dispose() {
        super.dispose();
        pressed.dispose();
        for (BotonesMenu boton : botones) {
            boton.dispose();
        }
    }

    public boolean verifyTouch(int x, int y) {
        if (isPress) {
            if (botones[0].isSeleccionado(x, y)) {
                return true;
            } else if (botones[1].isSeleccionado(x, y)) {
                // music = 1-music
                XMLReader.changeMusic();
            } else if (botones[2].isSeleccionado(x, y)){
                XMLReader.changeSound();
            } else {
                isSeleccionado(x, y);
            }
        } else {
            isSeleccionado(x, y);
        }
        return false;
    }
}
