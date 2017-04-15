package com.util;

import com.badlogic.gdx.graphics.Texture;
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

    @Override
    public boolean isSeleccionado(int x, int y) {
        boolean selecion = super.isSeleccionado(x, y);
        if( selecion ){
            Texture temp = otherTexture;
            otherTexture = noPulsado;
            noPulsado = temp;
        }
        return selecion;
    }

    @Override
    public void dispose() {
        super.dispose();
        otherTexture.dispose();
    }
}
