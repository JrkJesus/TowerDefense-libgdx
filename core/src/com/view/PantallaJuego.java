package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.*;
import com.util.Constants;

import java.util.Random;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    ControllerGame player;
    Texture background, winner, loser;

    public PantallaJuego(MainTowerDeffense _mtd) {
        super(_mtd);
        player = new ControllerGame(1);
        background = new Texture(Gdx.files.internal("Paths\\path_stage2.png"));
        winner = new Texture(Gdx.files.internal("Textures\\winner.png"));
        loser = new Texture(Gdx.files.internal("Textures\\loser.png"));
    }

    @Override
    public void render(float delta) {

        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);
        mtd.batch.end();

        player.draw(mtd.batch);

        if(player.isWinner()){
            mtd.batch.begin();
            mtd.batch.draw(winner, Gdx.graphics.getWidth()/2 - winner.getWidth()/2, Gdx.graphics.getHeight()/2 - winner.getHeight()/2);
            mtd.batch.end();
        } else if( player.isLoser()){
            mtd.batch.begin();
            mtd.batch.draw(loser, Gdx.graphics.getWidth()/2 - winner.getWidth()/2, Gdx.graphics.getHeight()/2 - winner.getHeight()/2);
            mtd.batch.end();
        }
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
