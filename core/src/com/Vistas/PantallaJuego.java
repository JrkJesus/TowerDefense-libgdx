package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by jesus on 15/03/2017.
 */

public class PantallaJuego extends PantallaBase {

    Texture fondo;
    Enemy[] enemies;
    ArrayList<Turret> turrets;
    
    public PantallaJuego(MainTowerDeffense _mtd, int fase) {
        super(_mtd);
        fondo = new Texture(Gdx.files.internal("towerDefense_backgroun" + String.format("%02d", fase)+ ".png"));
        switch(fase){
            case this.FASE1: 
                break;
            case this.FASE2:
                break;
            case this.FASE3:
                break;
        }
        turrets = new ArrayList<turret>();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.setProjectionMatrix(cam.combined);

        mtd.batch.begin();
        mtd.batch.draw(img, 200, 200);
        mtd.batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        fondo.dispose();
    }
}
