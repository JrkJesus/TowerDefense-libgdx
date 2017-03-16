package com.vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaSeleccion extends PantallaBase
{

    private Texture buttonPress, buttonTopScore, background;
    private final int buttonPressWidht, buttonPressHeight, width, height;

    public PantallaSeleccion(MainTowerDeffense m) {
        super(m);
        buttonPress = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        background = new Texture(Gdx.files.internal("Buttons\\background.png"));
       // buttonTopScore = new Texture(Gdx.files.internal("Buttons\\ScoreButton.png"));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        buttonPressWidht = width / 2 - buttonPress.getWidth() / 2;
        buttonPressHeight = height - (height / 8 + buttonPress.getWidth() / 8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();

        scaleFont(mtd.font, 2);
        mtd.batch.draw(background, 0, 0, width, height);
        //addButton(buttonTopScore,  " ", 150, 300, 0, 0);
        addButton(buttonPress, "Menu Principal", buttonPressWidht, (height - buttonPressHeight), Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);

        addButton(buttonPress, "Modo survival", buttonPressWidht, buttonPressHeight, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
        addButton(buttonPress, "Fase 1", buttonPressWidht, buttonPressHeight - 100, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
        addButton(buttonPress, "Fase 2", buttonPressWidht, buttonPressHeight - 200, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);
        addButton(buttonPress, "Fase 3",buttonPressWidht, buttonPressHeight - 300, Constants.BUTTONPRESS_X, Constants.BUTTONPRESS_Y);


        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, buttonPressHeight -100) ){
            mtd.setScreen(new PantallaJuego(mtd, Constants.FASE1));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, buttonPressHeight - 200) ){
            mtd.setScreen(new PantallaJuego(mtd, Constants.FASE2));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, buttonPressHeight - 300) ){
            System.out.println("Fase 3");
            mtd.setScreen(new PantallaJuego(mtd, Constants.FASE3));
        }
//        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, buttonPressHeight) ){
//             mtd.setScreen(new PantallaJuegoSurvival(mtd));
//        }
//        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, 300)){
//            mtd.setScreen(new PantallaPuntuación(mtd));
//        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, buttonPressWidht, (height - buttonPressHeight))){
            mtd.setScreen(new PantallaPrincipal(mtd));
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        buttonPress.dispose();
        buttonTopScore.dispose();
    }
}