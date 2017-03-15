package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class MenuPrincipal extends PantallaBase
{

    public MenuPrincipal(MainTowerDeffense m) {
        super(m);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.begin();
        mtd.font.draw(mtd.batch, "Bienvenido al TowerDeffense desarrollado por Antonio, Juanfra y Jesus", 100,150);
        mtd.font.draw(mtd.batch, "Pulsa en cualquier sitio para comenzar", 100, 100);
        mtd.batch.end();

        if( Gdx.input.isTouched()){
            mtd.setScreen(new PantallaJuego(mtd));
        }
    }


}
