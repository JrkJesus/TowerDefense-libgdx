package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.*;
import com.util.Constants;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    private int dificulty;
    private ControllerGame player;
    private Texture background, winner, loser, circle;

    public PantallaJuego(MainTowerDeffense _mtd) {
        super(_mtd);
        Constants.ESCALA_X = (float) ( width/Constants.GRID_WIDTH ) / (float) Constants.GRID_SIZE;
        Constants.ESCALA_Y = (float) ( height/Constants.GRID_HEIGH ) / (float) Constants.GRID_SIZE;
        Constants.GRID_RESIZE_X *=  Constants.ESCALA_X;
        Constants.GRID_RESIZE_Y *=  Constants.ESCALA_Y;
        dificulty = 1;
        player = new ControllerGame(dificulty);
        background = new Texture(Gdx.files.internal("Paths\\path_survival.png"));
        winner = new Texture(Gdx.files.internal("Textures\\winner.png"));
        loser = new Texture(Gdx.files.internal("Textures\\loser.png"));
        font.getData().setScale(Constants.ESCALA_X,Constants.ESCALA_Y);

        // TODO: 08/04/2017 Quitar el el pintar circulo
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        for (int i = 0; i < Constants.GRID_HEIGH; i++) {
            pixmap.drawLine(0, Constants.GRID_RESIZE_Y*i, width, Constants.GRID_RESIZE_Y*i);
        }
        for (int i = 0; i < Constants.GRID_WIDTH; i++) {
            pixmap.drawLine(Constants.GRID_RESIZE_X*i, 0, Constants.GRID_RESIZE_X*i, height);
        }
        circle = new Texture(pixmap);

    }

    @Override
    public void render(float delta) {

        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);
        font.draw(mtd.batch, "Vida: " + Integer.toString(player.getLife()), 25, height - 15 );
        font.draw(mtd.batch, "Dinero: " + Integer.toString(player.getMoney()), 25, height - 30 );
        font.draw(mtd.batch, "Puntuacion: " + Integer.toString(player.getScore()), 25, height - 45 );

        if(player.isWinner()){
            player.newWave(dificulty);
            player.addScore();
        } else if( player.isLoser()){
            mtd.batch.draw(loser, Gdx.graphics.getWidth()/2 - winner.getWidth()/2, Gdx.graphics.getHeight()/2 - winner.getHeight()/2);
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
