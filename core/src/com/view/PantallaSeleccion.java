package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaSeleccion extends PantallaBase
{
    private final int width, height;
    private TextButton.TextButtonStyle styleBtn;
    private TextButton stage1, stage2, stage3, survival;
    private VerticalGroup vg;

    public PantallaSeleccion(MainTowerDeffense m) {
        super(m);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        styleBtn = new TextButton.TextButtonStyle();
        vg = new VerticalGroup();
    }

    @Override
    public void show() {
        super.show();
        styleBtn.font = font;
        styleBtn.up = skin.getDrawable("button");
        styleBtn.down = skin.getDrawable("button-pressed");

        stage1 = new TextButton("Fase 1", styleBtn);

        stage2 = new TextButton("Fase 2", styleBtn);

        stage3 = new TextButton("Fase 3", styleBtn);

        survival = new TextButton("Survival", styleBtn);

        vg.addActor(survival);
        vg.addActor(stage1);
        vg.addActor(stage2);
        vg.addActor(stage3);

        vg.setFillParent(true);

        vg.setPosition(0, -height/4);
        vg.setWidth(width/4);
        vg.setHeight(75);

        stage.addActor(vg);

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