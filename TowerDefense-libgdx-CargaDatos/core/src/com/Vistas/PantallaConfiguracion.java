package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaConfiguracion extends PantallaBase
{
    private Texture button, background;
    private final int buttonPressWidht, buttonPressHeight, width, height;


    public PantallaConfiguracion(MainTowerDeffense _mtd) {
        super(_mtd);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        background = new Texture(Gdx.files.internal("Buttons\\background.png"));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        buttonPressWidht = width / 2 - button.getWidth() / 2;
        buttonPressHeight = height - (height / 2 + button.getWidth() / 2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);

        scaleFont(mtd.font, 3);
        mtd.font.draw(mtd.batch, "Configuracion", buttonPressWidht, height - buttonPressHeight/2);
        scaleFont(mtd.font, 2);

        addButton(button, "Volver a Inicio", buttonPressWidht, buttonPressHeight, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);


        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, buttonPressWidht, buttonPressHeight)){
            mtd.setScreen(new PantallaPrincipal(mtd));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
