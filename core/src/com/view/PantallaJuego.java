package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.models.Map;
import com.towerdeffense.*;
import com.util.Constants;


/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    private int dificulty;
    private ControllerGame player;
    private Texture winner, loser, square;
    private Map map;
    private Pixmap pixmap;

    public PantallaJuego(MainTowerDeffense _mtd, int dificulty) {
        super(_mtd);

        this.dificulty = dificulty;
        player = new ControllerGame(dificulty);
        map = new Map();
        winner = new Texture(Gdx.files.internal("Textures\\winner.png"));
        loser = new Texture(Gdx.files.internal("Textures\\loser.png"));
        font.getData().setScale(2.5f, 2.5f);
        pixmap = new Pixmap(200, (int) font.getXHeight()*4, Pixmap.Format.RGBA8888);
        pixmap.setColor(0,0,0,0.5f);
        pixmap.drawRectangle(0,0,width,height);
        square = new Texture(pixmap);
    }

    public PantallaJuego(MainTowerDeffense _mtd) {
        this(_mtd, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mtd.batch.begin();
        map.draw(mtd.batch);

        if (player.isWinner()) {
            player.newWave(dificulty);
            player.addScore();
        } else if (player.isLoser()) {
            mtd.batch.draw(loser, Gdx.graphics.getWidth() / 2 - winner.getWidth() / 2, Gdx.graphics.getHeight() / 2 - winner.getHeight() / 2);
        } else {
            player.draw(mtd.batch);
        }
        mtd.batch.end();
    }


    @Override
    public void dispose() {
        super.dispose();
        player.dispose();
        background.dispose();
        winner.dispose();
        loser.dispose();
    }
}
