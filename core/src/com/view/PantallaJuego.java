package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.models.Map;
import com.towerdeffense.*;
import com.util.Constants;


/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    private int dificulty;
    private ControllerGame player;
    private Texture winner, loser, circle;
    private Map map;
    private Pixmap pixmap;

    public PantallaJuego(MainTowerDeffense _mtd) {
        super(_mtd);

        dificulty = 1;
        player = new ControllerGame(dificulty);
        map = new Map();
        winner = new Texture(Gdx.files.internal("Textures\\winner.png"));
        loser = new Texture(Gdx.files.internal("Textures\\loser.png"));
        font.getData().setScale(Constants.ESCALA_X, Constants.ESCALA_Y);
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        for (int i = 0; i < Constants.GRID_WIDTH; i++) {
            pixmap.drawLine(Constants.GRID_RESIZE_X*i, 0, Constants.GRID_RESIZE_X*i, height);
        }
        for (int i = 0; i < Constants.GRID_HEIGH; i++) {
            pixmap.drawLine(0, height-Constants.GRID_RESIZE_Y*i, width, height-Constants.GRID_RESIZE_Y*i);
        }
        circle = new Texture(pixmap);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mtd.batch.begin();
        map.draw(mtd.batch);
        font.draw(mtd.batch, "Vida: " + Integer.toString(player.getLife()), 25, height - 15);
        font.draw(mtd.batch, "Dinero: " + Integer.toString(player.getMoney()), 25, height - 30);
        font.draw(mtd.batch, "Puntuacion: " + Integer.toString(player.getScore()), 25, height - 45);

        if (player.isWinner()) {
            player.newWave(dificulty);
            player.addScore();
        } else if (player.isLoser()) {
            mtd.batch.draw(loser, Gdx.graphics.getWidth() / 2 - winner.getWidth() / 2, Gdx.graphics.getHeight() / 2 - winner.getHeight() / 2);
        } else {
            player.draw(mtd.batch);
        }
        mtd.batch.draw(circle, 0, 0);
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
