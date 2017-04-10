package com.Limpieza;

import com.models.Map;
import com.models.Tile;
import com.towerdeffense.ControllerGame;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;
import com.view.PantallaBase;

/**
 * Created by jesus on 09/04/2017.
 */

public class Pantalla extends PantallaBase {

    private ControllerGame player;
    private Map map;

    public Pantalla(MainTowerDeffense _mtd) {
        super(_mtd);
        map = new Map();
        player = new ControllerGame(1);
    }

    @Override
    public void render(float delta) {
        mtd.batch.begin();
        map.draw(mtd.batch);
        mtd.batch.end();
    }


    @Override
    public void dispose() {
        super.dispose();
        map.dispose();
        player.dispose();
    }
}
