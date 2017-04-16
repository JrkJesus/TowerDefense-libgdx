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

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jesus on 16/04/2017.
 */

public class PantallaClasificacion extends PantallaBase {

    private final Texture win;
    private final BotonesMenu btnBack;
    Integer[] scores;

    public PantallaClasificacion(MainTowerDeffense _mtd) {
        super(_mtd);
        Integer[] clasification = XMLReader.getScore();
        Arrays.sort(clasification, Collections.reverseOrder());
        int mostrar = 5;
        if (clasification.length <= mostrar) {
            scores = clasification;
        } else {
            scores = Arrays.copyOf(clasification, mostrar);
        }
        XMLReader.setScore(scores);

        win = new Texture(Gdx.files.internal("GUI\\clasificacion.png"));
        btnBack = new BotonesMenu(new Texture(Gdx.files.internal("GUI\\back.png")), new Vector2(width / 2 - win.getWidth() / 4, height / 2 - win.getHeight() / 2.15f ));

        font = new BitmapFont(Gdx.files.internal("GUI\\font-title-export.fnt"), Gdx.files.internal("GUI\\font-title-export.png"), false);
        font.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0);
        mtd.batch.draw(win, width / 2 - win.getWidth() / 2, height / 2 - win.getHeight() / 2);
        int i = -Math.round(scores.length/2)+1;
        GlyphLayout text = new GlyphLayout();
        for (Integer score : scores) {
            text.setText(font, Integer.toString(score));
            font.draw(mtd.batch, text, (width - text.width)/2, (height + text.height)/ 2 + 20 - 40 * i++);
        }
        btnBack.draw(mtd.batch);

        mtd.batch.end();
        verifyButtonGameOverPress();
    }

    public void verifyButtonGameOverPress() {
        if (Gdx.input.justTouched()) {
            int posX = Gdx.input.getX(),
                    posY = (Gdx.graphics.getHeight() - Gdx.input.getY());
            if(btnBack.isSeleccionado(posX,posY)){
                mtd.setScreen(new PantallaPrincipal(mtd));
            }

        }
    }
}
