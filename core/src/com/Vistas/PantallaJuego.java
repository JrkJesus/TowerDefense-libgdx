package com.vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.models.Enemy;
import com.models.Turret;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

import java.util.ArrayList;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {

    Texture fondo;
    Array<Enemy> enemies;
    Array<Turret> turrets;
    Sprite sprite;
    
    public PantallaJuego(MainTowerDeffense _mtd, int fase) {
        super(_mtd);
        fondo = new Texture(Gdx.files.internal("Paths\\towerDefense_background"+ String.format("%02d", fase)+ ".png"));
        switch(fase){
            case Constants.FASE1:
                break;
            case Constants.FASE2:
                break;
            case Constants.FASE3:
                break;
        }
        enemies = new Array<Enemy>();
        sprite = new Sprite(new Texture(Gdx.files.internal("Sprites\\enemy01.png")));
// TODO: 16/03/2017 ver como se crean los sprites en libgdx
// TODO: 16/03/2017 añadir como estatico las rutas de los mapas. Aqui o en Constants.
//       enemies.add(new Enemy(sprite));
        turrets = new Array<Turret>();
        cam.rotate(270);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.setProjectionMatrix(cam.combined);

        mtd.batch.begin();
        mtd.batch.draw(fondo, 0, 0);
        mtd.batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        fondo.dispose();
        sprite.getTexture().dispose();
    }
}
