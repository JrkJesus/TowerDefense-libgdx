package com.view;

import com.badlogic.gdx.Gdx;
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
    private Texture background, winner, loser;

    public PantallaJuego(MainTowerDeffense _mtd) {
        super(_mtd);
        dificulty = 1;
        player = new ControllerGame(dificulty);
        background = new Texture(Gdx.files.internal("Paths\\path_stage2.png"));
        winner = new Texture(Gdx.files.internal("Textures\\winner.png"));
        loser = new Texture(Gdx.files.internal("Textures\\loser.png"));
        Constants.ESCALA_X = width/20;
        Constants.ESCALA_Y = height/11;
        font.getData().setScale(Constants.ESCALA_X/64,Constants.ESCALA_Y/64);
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
