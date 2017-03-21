package com.util;

import com.badlogic.gdx.Gdx;
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


    public static Array<Vector2> PATH_FASE1() {
        Array<Vector2> path = new Array<Vector2>();
        path.add(new Vector2(0,200));
        path.add(new Vector2(Gdx.graphics.getWidth(), 200));

        return path;
    }


}
