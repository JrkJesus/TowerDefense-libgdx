package com.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.towerdeffense.ControllerGame;


/**
 * Created by jesus on 09/04/2017.
 */

public class Map {

    private Tile[] map;

    public Map(){
        map=GENERATEMAP();
    }

    public void update(ControllerGame control) {
        for (Tile tile : map) {
            tile.update(control);
        }
    }

    public void draw(Batch batch) {
        for (Tile tile : map) {
            tile.draw(batch);
        }
    }

    public static Tile[] GENERATEMAP() {
        Tile[] map = new Tile[]{
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 0, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 0, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 1, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 1, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 2, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 2, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 3, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 3, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 3, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 3, 3, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 3, 4, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 3, 5, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 3, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 3, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 4, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 4, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 5, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 5, 1, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 5, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 5, 3, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 5, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 5, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 5, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 5, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 6, 0, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 6, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 6, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 6, 3, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 6, 4, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 6, 5, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 6, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 6, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 7, 0, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 7, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 8, 0, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 8, 7, false),


                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 9, 0, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 1, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 3, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 4, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 5, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 9, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 9, 7, true),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 10, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 10, 7, true),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 2, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 11, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 11, 7, true),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 12, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 12, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 12, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 12, 3, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 12, 4, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 12, 5, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\up.png")), 12, 6, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\giro.png")), 12, 7, true),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 13, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 13, 7, false),

                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 0, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 1, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\left.png")), 14, 2, true),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 3, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 4, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 5, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 6, false),
                new Tile(new Texture(Gdx.files.internal("Paths\\ground.png")), 14, 7, false),
        };

        map[3 * 8 + 6].rotate(180);
        map[6 * 8 + 6].rotate(90);
        map[5 * 8 + 3].rotate(180);
        map[5 * 8].rotate(270);
        map[9 * 8 + 7].rotate(180);
        map[12 * 8 + 7].rotate(90);
        map[12 * 8 + 2].rotate(270);

        return map;
    }

    public void dispose() {
        for (Tile tile: map)
        {
            tile.dispose();
        }
    }
}
