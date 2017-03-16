package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaPrincipal extends PantallaBase
{
    private Texture buttonPress, buttonTopScore;
    private static int MARGINBUTTONX = 35, MARGINBUTTONY = 55;
    private int width, height;

    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        buttonPress = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        buttonTopScore = new Texture(Gdx.files.internal("Buttons\\ScoreButton.png"));
        witdh = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.begin();
        
        scaleFont(mtd.font, 3);
        
        addButton(buttonTopScore, 150, 300, " ", 0, 0);
        addButton(buttonPress, 150, 200, "Volver al menu Principal", this.MARGINBUTTONX, this.MARGINBUTTONY);
        
        addButton(buttonPress, 150, height - 100, "Modo survival", this.MARGINBUTTONX, this.MARGINBUTTONY);
        addButton(buttonPress, 150, height - 200, "Fase 1", this.MARGINBUTTONX, this.MARGINBUTTONY);
        addButton(buttonPress, 150, height - 300, "Fase 2", this.MARGINBUTTONX, this.MARGINBUTTONY);
        addButton(buttonPress, 150, height - 400, "Fase 3", this.MARGINBUTTONX, this.MARGINBUTTONY);

       
        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, height - 200) ){
            mtd.setScreen(new PantallaJuego(mtd, PantallaJuego.FASE1));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, height - 300) ){
            mtd.setScreen(new PantallaJuego(mtd, PantallaJuego.FASE2));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, height - 400) ){
            mtd.setScreen(new PantallaJuego(mtd, PantallaJuego.FASE3));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, height - 100) ){
            // mtd.setScreen(new PantallaJuegoSurvival(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, 300{
            mtd.setScreen(new PantallaPuntuación(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(buttonPress, 150, 200{
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
