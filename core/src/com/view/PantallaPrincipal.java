package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.towerdeffense.MainTowerDeffense;
import com.util.BotonesMenu;
import com.util.DobleBoton;
import com.util.Multibotones;

/**
 * Created by jesus on 15/04/2017.
 */

public class PantallaPrincipal extends PantallaBase {

    BotonesMenu facil, medio, dificil;
    Multibotones setting;

    public PantallaPrincipal(MainTowerDeffense _mtd) {
        super(_mtd);
        facil = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\easy.png")), new Vector2(width/2, height - 2 * height / 4));
        medio = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\medium.png")), new Vector2(width/2, height - (int)(2.5 * height / 4)));
        dificil = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\hard.png")), new Vector2(width/2, height - 3 * height / 4));
        setting = new Multibotones();
        background = new Texture(Gdx.files.internal("GUI\\background.png"));
    }

    @Override
    public void render(float delta) {
        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0);
        facil.draw(mtd.batch);
        medio.draw(mtd.batch);
        dificil.draw(mtd.batch);
        setting.draw(mtd.batch);
        mtd.batch.end();

        verifyTouch();
    }

    @Override
    public void show() {
        super.show();
        mainMenuMusic.setLooping(true);
        mainMenuMusic.play();
    }


    public void verifyTouch(){
        if( Gdx.input.justTouched() ){
            int x = Gdx.input.getX(),
                    y = height - Gdx.input.getY();

            if(facil.isSeleccionado(x, y)){
                mtd.setScreen(new PantallaJuego(mtd, 1));
            } else if (medio.isSeleccionado(x, y)){
                mtd.setScreen(new PantallaJuego(mtd, 2));
            } else if (dificil.isSeleccionado(x, y)){
                mtd.setScreen(new PantallaJuego(mtd, 3));
            }

            setting.verifyTouch(x,y);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        facil.dispose();
        medio.dispose();
        dificil.dispose();
        setting.dispose();
    }
}
