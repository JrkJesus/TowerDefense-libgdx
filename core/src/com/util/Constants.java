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
    private static RandomXS128 rnd = new RandomXS128();

    public final static int FASE1 = 1;
    public final static int FASE2 = 2;
    public final static int FASE3 = 3;

    public static float ESCALA_X = 64;
    public static float ESCALA_Y = 64;

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

    public static Array<Vector2> PATH_FASE1() {
        Array<Vector2> path = new Array<Vector2>();
        path.add(new Vector2(0,3));
        path.add(new Vector2(1,3));
        path.add(new Vector2(2,3));
        path.add(new Vector2(2,4));
        path.add(new Vector2(2,5));
        path.add(new Vector2(2,6));
        path.add(new Vector2(2,7));
        path.add(new Vector2(2,8));
        path.add(new Vector2(2,9));
        path.add(new Vector2(3,9));
        path.add(new Vector2(4,9));
        path.add(new Vector2(5,9));
        path.add(new Vector2(6,9));
        path.add(new Vector2(7,9));
        path.add(new Vector2(7,8));
        path.add(new Vector2(7,7));
        path.add(new Vector2(7,6));
        path.add(new Vector2(6,6));
        path.add(new Vector2(5,6));
        path.add(new Vector2(5,5));
        path.add(new Vector2(5,4));
        path.add(new Vector2(5,3));
        path.add(new Vector2(6,3));
        path.add(new Vector2(7,3));
        path.add(new Vector2(7,2));
        path.add(new Vector2(7,1));
        path.add(new Vector2(8,1));
        path.add(new Vector2(9,1));
        path.add(new Vector2(10,1));
        path.add(new Vector2(11,1));
        path.add(new Vector2(12,1));
        path.add(new Vector2(12,2));
        path.add(new Vector2(12,3));
        path.add(new Vector2(13,3));
        path.add(new Vector2(14,3));
        path.add(new Vector2(14,4));
        path.add(new Vector2(14,5));
        path.add(new Vector2(14,6));
        path.add(new Vector2(13,6));
        path.add(new Vector2(12,6));
        path.add(new Vector2(12,7));
        path.add(new Vector2(12,8));
        path.add(new Vector2(12,9));
        path.add(new Vector2(13,9));
        path.add(new Vector2(14,9));
        path.add(new Vector2(15,9));
        path.add(new Vector2(16,9));
        path.add(new Vector2(17,9));
        path.add(new Vector2(17,8));
        path.add(new Vector2(17,7));
        path.add(new Vector2(17,6));
        path.add(new Vector2(17,5));
        path.add(new Vector2(17,4));
        path.add(new Vector2(17,3));
        path.add(new Vector2(18,3));
        path.add(new Vector2(19,3));
        path.add(new Vector2(20,3));

        return path;
    }


}
