package com.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by jesus on 16/03/2017.
 */

public final class Constants {
    public static RandomXS128 rnd = new RandomXS128();

    public final static int FASE1 = 1;
    public final static int FASE2 = 2;
    public final static int FASE3 = 3;

    public final static int GRID_SIZE = 225;
    public final static int GRID_WIDTH = 8;
    public final static int GRID_HEIGH = 5;

    public static float ESCALA_X = 1;
    public static float ESCALA_Y = 1;
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
        int initHeight = (rnd.nextInt(9))+3,
                endHeigth = (rnd.nextInt(9))+3;
        path.add(new Vector2(0, initHeight));
        path.add(new Vector2(20, endHeigth));

        return path;
    }

    //Camino Survival
    public static Array<Vector2> PATH_FASE1() {
        Array<Vector2> path = new Array<Vector2>();
        path.add(new Vector2(0,1));
        path.add(new Vector2(1,1));
        path.add(new Vector2(1,2));
        path.add(new Vector2(1,3));
        path.add(new Vector2(1,4));
        path.add(new Vector2(2,4));
        path.add(new Vector2(3,4));
        path.add(new Vector2(3,3));
        path.add(new Vector2(3,2));
        path.add(new Vector2(2,2));
        path.add(new Vector2(2,1));
        path.add(new Vector2(2,0));
        path.add(new Vector2(3,0));
        path.add(new Vector2(4,0));
        path.add(new Vector2(5,0));
        path.add(new Vector2(5,1));
        path.add(new Vector2(5,2));
        path.add(new Vector2(5,3));
        path.add(new Vector2(5,4));
        path.add(new Vector2(6,4));
        path.add(new Vector2(7,4));
        path.add(new Vector2(7,3));
        path.add(new Vector2(7,2));
        path.add(new Vector2(7,1));
        path.add(new Vector2(7,0));
        path.add(new Vector2(8,0));
        return path;
    }


}
