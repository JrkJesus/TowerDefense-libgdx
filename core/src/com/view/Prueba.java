package com.view;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by jesus on 22/03/2017.
 */

public class Prueba extends PantallaBase{

    private Table row;

    public Prueba(MainTowerDeffense _mtd) {
        super(_mtd);
        row = new Table();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
