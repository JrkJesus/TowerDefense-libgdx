package com.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.models.Tile;

/**
 * Created by jesus on 16/03/2017.
 */

public final class Constants {
    public static RandomXS128 rnd = new RandomXS128();

    public final static int FASE1 = 1;
    public final static int FASE2 = 2;
    public final static int FASE3 = 3;

    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int GIRO = 3;
    public static final int GROUND = 4;

    public final static int GRID_SIZE = 225;
    public final static int GRID_WIDTH = 8;
    public final static int GRID_HEIGH = 5;

    public static float ESCALA_X = 2;
    public static float ESCALA_Y = 2;
    public static int GRID_RESIZE_X = 225;
    public static int GRID_RESIZE_Y = 225;

    public final static int PLANE = 3;
    public final static int TANK = 8;
    public final static int PEOPLE = 4;
    
    public final static int ANTIAIR = 3;
    public final static int ANTITANK = 2;
    public final static int MACHINEGUN = 4;

    public static Array<Vector2> PATH_PLANE(){
        Array<Vector2> path = new Array<Vector2>();
        int initHeight = (rnd.nextInt(6)),
            endHeigth  = (rnd.nextInt(6));
        path.add(new Vector2(0, initHeight));
        path.add(new Vector2(20, endHeigth));

        return path;
    }

    //Camino Survival
    public static Array<Vector2> PATH_FASE1() {
        Array<Vector2> path = new Array<Vector2>();
        path.add(new Vector2(0,2));
        path.add(new Vector2(1,2));
        path.add(new Vector2(2,2));
        path.add(new Vector2(3,2));
        path.add(new Vector2(3,3));
        path.add(new Vector2(3,4));
        path.add(new Vector2(3,5));
        path.add(new Vector2(3,6));
        path.add(new Vector2(4,6));
        path.add(new Vector2(5,6));
        path.add(new Vector2(6,6));
        path.add(new Vector2(6,5));
        path.add(new Vector2(6,4));
        path.add(new Vector2(6,3));
        path.add(new Vector2(5,3));
        path.add(new Vector2(5,2));
        path.add(new Vector2(5,1));
        path.add(new Vector2(5,0));
        path.add(new Vector2(6,0));
        path.add(new Vector2(7,0));
        path.add(new Vector2(8,0));
        path.add(new Vector2(9,0));
        path.add(new Vector2(9,1));
        path.add(new Vector2(9,2));
        path.add(new Vector2(9,3));
        path.add(new Vector2(9,4));
        path.add(new Vector2(9,5));
        path.add(new Vector2(9,6));
        path.add(new Vector2(9,7));
        path.add(new Vector2(10,7));
        path.add(new Vector2(11,7));
        path.add(new Vector2(12,7));
        path.add(new Vector2(12,6));
        path.add(new Vector2(12,5));
        path.add(new Vector2(12,4));
        path.add(new Vector2(12,3));
        path.add(new Vector2(12,2));
        path.add(new Vector2(13,2));
        path.add(new Vector2(14,2));
        path.add(new Vector2(15,2));
        return path;
    }


    public static Tile[] GENERATEMAP() {
        Tile[] map = new Tile[]{
                new Tile(GROUND, 0, 0),
                new Tile(GROUND, 0, 1),
                new Tile(LEFT, 0, 2),
                new Tile(GROUND, 0, 3),
                new Tile(GROUND, 0, 4),
                new Tile(GROUND, 0, 5),
                new Tile(GROUND, 0, 6),
                new Tile(GROUND, 0, 7),

                new Tile(GROUND, 1, 0),
                new Tile(GROUND, 1, 1),
                new Tile(LEFT, 1, 2),
                new Tile(GROUND, 1, 3),
                new Tile(GROUND, 1, 4),
                new Tile(GROUND, 1, 5),
                new Tile(GROUND, 1, 6),
                new Tile(GROUND, 1, 7),

                new Tile(GROUND, 2, 0),
                new Tile(GROUND, 2, 1),
                new Tile(LEFT, 2, 2),
                new Tile(GROUND, 2, 3),
                new Tile(GROUND, 2, 4),
                new Tile(GROUND, 2, 5),
                new Tile(GROUND, 2, 6),
                new Tile(GROUND, 2, 7),

                new Tile(GROUND, 3, 0),
                new Tile(GROUND, 3, 1),
                new Tile(GIRO, 3, 2),
                new Tile(UP, 3, 3),
                new Tile(UP, 3, 4),
                new Tile(UP, 3, 5),
                new Tile(GIRO, 3, 6),
                new Tile(GROUND, 3, 7),

                new Tile(GROUND, 4, 0),
                new Tile(GROUND, 4, 1),
                new Tile(GROUND, 4, 2),
                new Tile(GROUND, 4, 3),
                new Tile(GROUND, 4, 4),
                new Tile(GROUND, 4, 5),
                new Tile(LEFT, 4, 6),
                new Tile(GROUND, 4, 7),

                new Tile(GIRO, 5, 0),
                new Tile(UP, 5, 1),
                new Tile(UP, 5, 2),
                new Tile(GIRO, 5, 3),
                new Tile(GROUND, 5, 4),
                new Tile(GROUND, 5, 5),
                new Tile(LEFT, 5, 6),
                new Tile(GROUND, 5, 7),

                new Tile(GIRO, 6, 0),
                new Tile(GROUND, 6, 1),
                new Tile(GROUND, 6, 2),
                new Tile(GIRO, 6, 3),
                new Tile(UP, 6, 4),
                new Tile(UP, 6, 5),
                new Tile(GIRO, 6, 6),
                new Tile(GROUND, 6, 7),

                new Tile(LEFT, 7, 0),
                new Tile(GROUND, 7, 1),
                new Tile(GROUND, 7, 2),
                new Tile(GROUND, 7, 3),
                new Tile(GROUND, 7, 4),
                new Tile(GROUND, 7, 5),
                new Tile(GROUND, 7, 6),
                new Tile(GROUND, 7, 7),

                new Tile(LEFT, 8, 0),
                new Tile(GROUND, 8, 1),
                new Tile(GROUND, 8, 2),
                new Tile(GROUND, 8, 3),
                new Tile(GROUND, 8, 4),
                new Tile(GROUND, 8, 5),
                new Tile(GROUND, 8, 6),
                new Tile(GROUND, 8, 7),


                new Tile(GIRO, 9, 0),
                new Tile(UP, 9, 1),
                new Tile(UP, 9, 2),
                new Tile(UP, 9, 3),
                new Tile(UP, 9, 4),
                new Tile(UP, 9, 5),
                new Tile(UP, 9, 6),
                new Tile(GIRO, 9, 7),

                new Tile(GROUND, 10, 0),
                new Tile(GROUND, 10, 1),
                new Tile(GROUND, 10, 2),
                new Tile(GROUND, 10, 3),
                new Tile(GROUND, 10, 4),
                new Tile(GROUND, 10, 5),
                new Tile(GROUND, 10, 6),
                new Tile(LEFT, 10, 7),

                new Tile(GROUND, 11, 0),
                new Tile(GROUND, 11, 1),
                new Tile(GROUND, 11, 2),
                new Tile(GROUND, 11, 3),
                new Tile(GROUND, 11, 4),
                new Tile(GROUND, 11, 5),
                new Tile(GROUND, 11, 6),
                new Tile(LEFT, 11, 7),

                new Tile(GROUND, 12, 0),
                new Tile(GROUND, 12, 1),
                new Tile(GIRO, 12, 2),
                new Tile(UP, 12, 3),
                new Tile(UP, 12, 4),
                new Tile(UP, 12, 5),
                new Tile(UP, 12, 6),
                new Tile(GIRO, 12, 7),

                new Tile(GROUND, 13, 0),
                new Tile(GROUND, 13, 1),
                new Tile(LEFT, 13, 2),
                new Tile(GROUND, 13, 3),
                new Tile(GROUND, 13, 4),
                new Tile(GROUND, 13, 5),
                new Tile(GROUND, 13, 6),
                new Tile(GROUND, 13, 7),

                new Tile(GROUND, 14, 0),
                new Tile(GROUND, 14, 1),
                new Tile(LEFT, 14, 2),
                new Tile(GROUND, 14, 3),
                new Tile(GROUND, 14, 4),
                new Tile(GROUND, 14, 5),
                new Tile(GROUND, 14, 6),
                new Tile(GROUND, 14, 7),
        };

        map[3*8+6].rotate(180);
        map[6*8+6].rotate(90);
        map[6*8].rotate(270);
        map[5*8+3].rotate(180);
        map[5*8].rotate(270);
        map[9*8+7].rotate(180);
        map[12*8+7].rotate(90);
        map[12*8+2].rotate(270);

        return map;
    }
}
