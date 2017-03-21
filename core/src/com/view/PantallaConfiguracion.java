package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaConfiguracion extends PantallaBase
{
    private final int width, height;
    private final VerticalGroup vg;
    private TextButton music, home, easy, normal, hard;
    // TODO: 22/03/2017 Añadir styles (radioBtn y scroll) 


    public PantallaConfiguracion(MainTowerDeffense _mtd) {
        super(_mtd);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        vg = new VerticalGroup();

   }

    @Override
    public void show() {
        super.show();
        vg.setFillParent(true);
        style.up = skin.getDrawable("scroll-bar-back-horizontal");
        music = new TextButton("Volumen", style);
        addButton(music, 3*width/8, height-height/5, width/4, 75);
        vg.addActor(music);

        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("button-pressed");

        home = new TextButton("Volver", style);
        addButton(home, 3*width/8, height/5, width/4, 75);

        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                mtd.setScreen(new PantallaPrincipal(mtd));
            }
        });

        stage.addActor(vg);
        stage.addActor(home);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);
        mtd.batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
