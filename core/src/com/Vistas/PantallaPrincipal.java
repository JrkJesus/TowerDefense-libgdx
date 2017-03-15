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
    private Texture button;
    private static int MARGINBUTTONX = 35, MARGINBUTTONY = 55;

    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.begin();
        mtd.batch.draw(button, 150, 300);
        scaleFont(mtd.font, 2);
        mtd.font.draw(mtd.batch, "Iniciar Juego", 150 + PantallaPrincipal.MARGINBUTTONX, 300 + PantallaPrincipal.MARGINBUTTONY);
        mtd.batch.draw(button, 150, 150);
        mtd.font.draw(mtd.batch, "Configuracion", 150 + PantallaPrincipal.MARGINBUTTONX, 150 + PantallaPrincipal.MARGINBUTTONY);
        scaleFont(mtd.font, 1f, 0.75f);
        mtd.font.draw(mtd.batch, "TowerDeffense desarrollado por Antonio, Juan Francisco y Jesus \n para la Universidad de Huelva", 100,50);
        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, 100, 300)){
            mtd.setScreen(new PantallaJuego(mtd));
            System.out.println("Cambiando a pantalla de juego");
        }
        if( Gdx.input.justTouched() && isButtonPress(button, 100, 150)){
            mtd.setScreen(new PantallaConfiguracion(mtd));
            System.out.println("Cambiando a Pantalla de configuracion");
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
