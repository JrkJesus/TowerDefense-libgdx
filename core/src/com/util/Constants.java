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
    public final static int FASE1 = 1;
    public final static int FASE2 = 2;
    public final static int FASE3 = 3;

    public final static int BUTTONPRESS_X = 35;
    public final static int BUTTONPRESS_Y = 55;
    
    public final static int PLANE = 1;
    public final static int TANK = 2;
    public final static int PEOPLE = 3;
    
    public final static int ANTIAIR = 1;
    public final static int ANTITANK = 2;
    public final static int MACHINEGUN = 3;

    public static Array<Vector2> PATH_PLANE(RandomXS128 rnd){
        Array<Vector2> path = new Array<Vector2>();
        int initHeight = (rnd.nextInt(9))+3,
                endHeigth = (rnd.nextInt(9))+3;
        path.add(new Vector2(0, initHeight));
        path.add(new Vector2(20, endHeigth));

        return path;
    }

    public static Array<Vector2> PATH_FASE1() {
        Array<Vector2> path = new Array<Vector2>();
        path.add(new Vector2(0,8));
        path.add(new Vector2(1,8));
        path.add(new Vector2(2,8));
        path.add(new Vector2(3,8));
        path.add(new Vector2(4,8));
        path.add(new Vector2(5,8));
        path.add(new Vector2(5,7));
        path.add(new Vector2(5,6));
        path.add(new Vector2(5,5));
        path.add(new Vector2(5,4));
        path.add(new Vector2(4,4));
        path.add(new Vector2(3,4));
        path.add(new Vector2(2,4));
        path.add(new Vector2(2,3));
        path.add(new Vector2(2,2));
        path.add(new Vector2(2,1));
        path.add(new Vector2(3,1));
        path.add(new Vector2(4,1));
        path.add(new Vector2(5,1));
        path.add(new Vector2(6,1));
        path.add(new Vector2(7,1));
        path.add(new Vector2(8,1));
        path.add(new Vector2(9,1));
        path.add(new Vector2(9,2));
        path.add(new Vector2(9,3));
        path.add(new Vector2(9,4));
        path.add(new Vector2(9,5));
        path.add(new Vector2(9,6));
        path.add(new Vector2(9,7));
        path.add(new Vector2(9,8));
        path.add(new Vector2(9,9));
        path.add(new Vector2(9,10));
        path.add(new Vector2(10,10));
        path.add(new Vector2(11,10));
        path.add(new Vector2(12,10));
        path.add(new Vector2(13,10));
        path.add(new Vector2(13,9));
        path.add(new Vector2(13,8));
        path.add(new Vector2(13,7));
        path.add(new Vector2(13,6));
        path.add(new Vector2(14,6));
        path.add(new Vector2(15,6));
        path.add(new Vector2(16,6));
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
