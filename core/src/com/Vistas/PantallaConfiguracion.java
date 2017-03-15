package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaConfiguracion extends PantallaBase
{
    Texture button;
    private static int MARGINBUTTONX = 70, MARGINBUTTONY = 90;


    public PantallaConfiguracion(MainTowerDeffense _mtd) {
        super(_mtd);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();
        scaleFont(mtd.font, 3);
        mtd.font.draw(mtd.batch, "Configuracion", 200, 450);
        scaleFont(mtd.font, 2);

        mtd.batch.draw(button, 100, 150);
        mtd.font.draw(mtd.batch, "Volver a Inicio", 100 + this.MARGINBUTTONX, 150 + this.MARGINBUTTONY);

        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, 150, 100)){
            mtd.setScreen(new PantallaPrincipal(mtd));
            System.out.println("Cambiando a pantalla principal");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
