package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.models.Map;
import com.towerdeffense.*;
import com.util.BotonesMenu;
import com.util.Constants;


/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {
    private int dificulty;
    private ControllerGame player;
    private Texture win;
    private Map map;
    private Music gameMusic;
    private BotonesMenu btnBack,
            btnRestart;

    public PantallaJuego(MainTowerDeffense _mtd, int dificulty) {
        super(_mtd);

        this.dificulty = dificulty;
        player = new ControllerGame(dificulty);
        map = new Map();

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds\\background-battle.ogg"));

        win = new Texture(Gdx.files.internal("GUI\\winner.png"));
        btnBack = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\back.png")), new Vector2(Gdx.graphics.getWidth() / 2 - win.getWidth() / 10, Gdx.graphics.getHeight() / 2 - win.getHeight() / 3));
        btnRestart = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\reload.png")), new Vector2(Gdx.graphics.getWidth() / 2 + win.getWidth() / 10, Gdx.graphics.getHeight() / 2 - win.getHeight() / 3));
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
            mtd.batch.draw(win, Gdx.graphics.getWidth() / 2 - win.getWidth() / 2, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2);

            GlyphLayout glyphLayout=new GlyphLayout();
            glyphLayout.setText(font,"Puntuacion");
            font.draw(mtd.batch,glyphLayout,(width - glyphLayout.width)/2, (height + glyphLayout.height)/ 2);
            glyphLayout.setText(font,Integer.toString(player.getScore()));
            font.draw(mtd.batch,glyphLayout,(width - glyphLayout.width)/2, (height + glyphLayout.height)/ 2 -40);

            btnBack.draw(mtd.batch);
            btnRestart.draw(mtd.batch);
            verifyButtonGameOverPress();
        } else {
            player.draw(mtd.batch);
        }

        mtd.batch.end();

    }

    @Override
    public void show() {
        super.show();
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    public void verifyButtonGameOverPress() {
        if (Gdx.input.justTouched()) {
            int posX = Gdx.input.getX(),
                    posY = (Gdx.graphics.getHeight() - Gdx.input.getY());
            if(btnBack.isSeleccionado(posX,posY)){
                mtd.setScreen(new PantallaPrincipal(mtd));
            }
            else if(btnRestart.isSeleccionado(posX,posY)){
                mtd.setScreen(new PantallaJuego(mtd,dificulty));
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        player.dispose();
        background.dispose();
        gameMusic.stop();
        gameMusic.dispose();
        btnBack.dispose();
        btnRestart.dispose();
    }
}
