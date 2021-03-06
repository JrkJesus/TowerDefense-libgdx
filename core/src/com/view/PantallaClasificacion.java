package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.towerdeffense.MainTowerDeffense;
import com.util.BotonesMenu;
import com.util.XMLReader;

/**
 * Created by jesus on 16/04/2017.
 */

public class PantallaClasificacion extends PantallaBase {

    private final Texture win;
    private final BotonesMenu btnBack, btnForward, btnHome;
    private Integer[] scores;
    private float volume;
    private int indx;

    public PantallaClasificacion(MainTowerDeffense _mtd) {
        super(_mtd);
        indx = 0;
        setScore();

        win = new Texture(Gdx.files.internal("GUI\\background-leaderboard.png"));
        btnBack = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\back.png")), new Vector2(width / 2 - win.getWidth() / 4, height / 2 - win.getHeight() / 2.15f ));
        btnForward = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\forward.png")), new Vector2(width / 2 + win.getWidth() / 4, height / 2 - win.getHeight() / 2.15f ));
        btnHome = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\home.png")), new Vector2(width / 2, height / 2 - win.getHeight() / 2.15f ));

        font = new BitmapFont(Gdx.files.internal("GUI\\font-button-export.fnt"));
        font.setColor(Color.BLACK);

        volume=XMLReader.readConfig().music;
    }

    private void setScore() {
        scores = XMLReader.readScore(indx).getScore();
    }

    @Override
    public void show() {
        super.show();
        mainMenuMusic.setLooping(true);
        mainMenuMusic.setVolume(volume);
        mainMenuMusic.play();
    }

    @Override
    public void render(float delta) {
        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0);
        mtd.batch.draw(win, width / 2 - win.getWidth() / 2, height / 2 - win.getHeight() / 2);
        int i = -Math.round(scores.length/2)+1;
        GlyphLayout text = new GlyphLayout();
        font.getData().setScale(3f, 1);
        if(indx == 0)
            text.setText(font, "Facil");
        else if(indx == 1)
            text.setText(font, "Normal");
        else
            text.setText(font, "Dificil");
        font.getData().setScale(1.5f);
        font.draw(mtd.batch, text, (width - text.width)/2, (height + text.height)/ 2 + 150);
        for (Integer score : scores) {
            text.setText(font, Integer.toString(score));
            font.draw(mtd.batch, text, (width - text.width)/2, (height + text.height)/ 2 + 10 - 50 * i++);
        }
        btnForward.draw(mtd.batch);
        btnHome.draw(mtd.batch);
        btnBack.draw(mtd.batch);

        mtd.batch.end();
        verifyButtonGameOverPress();
    }

    public void verifyButtonGameOverPress() {
        if (Gdx.input.justTouched()) {
            int posX = Gdx.input.getX(),
                    posY = (Gdx.graphics.getHeight() - Gdx.input.getY());
            if(btnHome.isSeleccionado(posX,posY)){
                mtd.setScreen(new PantallaPrincipal(mtd));
            } else  if(btnForward.isSeleccionado(posX,posY)){
                indx = (indx+1)%3;
                setScore();
            } else  if(btnBack.isSeleccionado(posX,posY)){
                indx--;
                if(indx<0){
                    indx = 2;
                }
                setScore();
            }

        }
    }
}
