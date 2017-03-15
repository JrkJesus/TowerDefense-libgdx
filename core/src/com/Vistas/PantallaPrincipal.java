package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.ShapeCache;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaPrincipal extends PantallaBase
{
    private Texture button, background;
    private static int MARGINBUTTONX = 35, MARGINBUTTONY = 55;


    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        background = new Texture(Gdx.files.internal("Buttons\\background.jpg"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        mtd.batch.begin();

        mtd.batch.draw(background, 0, 0);
        mtd.batch.draw(background, background.getWidth(), 0);
        mtd.batch.draw(button, 150, 300);
        scaleFont(mtd.font, 2);
        mtd.font.draw(mtd.batch, "Iniciar Juego", 150 + PantallaPrincipal.MARGINBUTTONX, 300 + PantallaPrincipal.MARGINBUTTONY);
        mtd.batch.draw(button, 150, 200);
        mtd.font.draw(mtd.batch, "Configuracion", 150 + PantallaPrincipal.MARGINBUTTONX, 200 + PantallaPrincipal.MARGINBUTTONY);
        mtd.batch.draw(button, 150, 100);
        mtd.font.draw(mtd.batch, "Salir", 150 + PantallaPrincipal.MARGINBUTTONX, 100 + PantallaPrincipal.MARGINBUTTONY);
        scaleFont(mtd.font, 1f, 0.5f);
        mtd.font.draw(mtd.batch, "\u00a9 TowerDeffense desarrollado por Antonio, Juan Francisco y Jesus", 50,20);

        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, 150, 300)){
            mtd.setScreen(new PantallaJuego(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, 150, 200)){
            mtd.setScreen(new PantallaConfiguracion(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, 150, 100)){
            Gdx.app.exit();
        }

    }


    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
