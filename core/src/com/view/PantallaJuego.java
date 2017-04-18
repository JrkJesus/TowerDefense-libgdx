package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.models.Map;
import com.towerdeffense.*;
import com.util.BotonesMenu;
import com.util.Constants;
import com.util.XMLReader;


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
            btnRestart,
            btnPause,
            btnBackMenu;
    private estate actual;

    enum estate {PAUSE, START}

    public PantallaJuego(MainTowerDeffense _mtd, int dificulty) {
        super(_mtd);

        this.dificulty = dificulty;
        player = new ControllerGame(dificulty);
        map = new Map();
        actual = estate.START;
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds\\background-battle.ogg"));

        win = new Texture(Gdx.files.internal("GUI\\winner.png"));
        btnBack = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\back.png")), new Vector2(Gdx.graphics.getWidth() / 2 - win.getWidth() / 4, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2.15f));
        btnRestart = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\reload.png")), new Vector2(Gdx.graphics.getWidth() / 2 + win.getWidth() / 4, Gdx.graphics.getHeight() / 2 -win.getHeight() / 2.15f));
        btnPause = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\pause.png")), new Vector2(Gdx.graphics.getWidth() - 64, Gdx.graphics.getHeight() - 64));
        btnBackMenu = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\back.png")), new Vector2(Gdx.graphics.getWidth() - btnPause.getWitdh()*1.5f - 64, Gdx.graphics.getHeight() - 64));
    }

    public PantallaJuego(MainTowerDeffense _mtd) {
        this(_mtd, 1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        verifyPause();
        mtd.batch.begin();
        map.draw(mtd.batch);
        if (player.isWinner()) {
            player.newWave(dificulty);
            player.addScore();
        } else if (player.isLoser()) {
            mtd.batch.draw(win, Gdx.graphics.getWidth() / 2 - win.getWidth() / 2, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2);

            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(font, "Puntuacion");
            font.draw(mtd.batch, glyphLayout, (width - glyphLayout.width) / 2, (height + glyphLayout.height) / 2);
            glyphLayout.setText(font, Integer.toString(player.getScore()));
            font.draw(mtd.batch, glyphLayout, (width - glyphLayout.width) / 2, (height + glyphLayout.height) / 2 - 40);

            btnBack.draw(mtd.batch);
            btnRestart.draw(mtd.batch);
            verifyButtonGameOverPress();
        } else {
            player.draw(mtd.batch);
        }
        btnPause.draw(mtd.batch);
        btnBackMenu.draw(mtd.batch);
        mtd.batch.end();
    }

    private void verifyPause() {
        if (Gdx.input.justTouched()) {
            int posX = Gdx.input.getX(),
                    posY = (Gdx.graphics.getHeight() - Gdx.input.getY());
            if (btnPause.isSeleccionado(posX, posY)) {
                Constants.DELTA_TIME = 0.025f - Constants.DELTA_TIME ;
            } else if (btnBackMenu.isSeleccionado(posX, posY)){
                mtd.setScreen(new PantallaPrincipal(mtd));
            }
        }
    }

    @Override
    public void show() {
        super.show();
        gameMusic.setLooping(true);
        gameMusic.setVolume(XMLReader.readConfig().music);
        gameMusic.play();
    }

    public void verifyButtonGameOverPress() {
        if (Gdx.input.justTouched()) {
            int posX = Gdx.input.getX(),
                    posY = (Gdx.graphics.getHeight() - Gdx.input.getY());
            if (btnBack.isSeleccionado(posX, posY)) {
                mtd.setScreen(new PantallaPrincipal(mtd));
                XMLReader.addScore(dificulty-1, player.getScore());
            } else if (btnRestart.isSeleccionado(posX, posY)) {
                mtd.setScreen(new PantallaJuego(mtd, dificulty));
                XMLReader.addScore(dificulty-1, player.getScore());
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
        btnPause.dispose();
        btnBackMenu.dispose();
    }
}
