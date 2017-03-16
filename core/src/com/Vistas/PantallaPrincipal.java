package com.vistas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaPrincipal extends PantallaBase
{
    private final int buttonPressWidht, buttonPressHeight, width, height;
    private Texture button, background;

    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        background = new Texture(Gdx.files.internal("Buttons\\background.png"));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        buttonPressWidht = width / 2 - button.getWidth() / 2;
        buttonPressHeight = height - (height / 5 + button.getWidth() / 2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);

        scaleFont(mtd.font, 2);
        addButton(button, "Selecionar Juego", buttonPressWidht, buttonPressHeight, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
        addButton(button, "Configuracion", buttonPressWidht, buttonPressHeight - 100, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
        addButton(button, "Salir", buttonPressWidht, buttonPressHeight - 200, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
       
        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, buttonPressWidht, buttonPressHeight) ){
            mtd.setScreen(new PantallaSeleccion(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, buttonPressWidht, buttonPressHeight - 100) ){
            mtd.setScreen(new com.vistas.PantallaConfiguracion(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, buttonPressWidht, buttonPressHeight - 200) ){
            Gdx.app.exit();
        }

    }


    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
