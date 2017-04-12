package com.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    public final static int GRID_SIZE = 128;
    public final static int GRID_WIDTH = 15;
    public final static int GRID_HEIGH = 8;

    public final static int MACHINE_COST=50;
    public final static int PLANE_COST=75;
    public final static int TANK_COST=100;

    public static float ESCALA_X = 1;
    public static float ESCALA_Y = 1;
    public static int GRID_RESIZE_X = 128;
    public static int GRID_RESIZE_Y = 128;

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

}
