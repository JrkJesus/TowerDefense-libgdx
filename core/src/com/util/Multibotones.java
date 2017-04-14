package com.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Antonio on 14/04/2017.
 */

public class Multibotones extends BotonesMenu{
    private BotonesMenu[] botones;
    private Texture pressed;

    public Multibotones(Texture noPulsado, Vector2 position) {
        super(noPulsado, position);
    }

    public Multibotones(){
        this(new Texture(Gdx.files.internal("GUI\\setting.png")),new Vector2(Gdx.graphics.getWidth()-74,Gdx.graphics.getHeight()-74));
        pressed=new Texture(Gdx.files.internal("GUI\\setting-pressed.png"));
        int x= Gdx.graphics.getWidth()-pressed.getWidth()-10,
            y= Gdx.graphics.getHeight()-pressed.getHeight()-10;

        botones=new BotonesMenu[]{
                new BotonesMenu(new Texture(Gdx.files.internal("GUI\\leaderboard.png")),new Vector2(x+10,y+10)),
                new BotonesMenu(new Texture(Gdx.files.internal("GUI\\leaderboard.png")),new Vector2(x+10,y+84))
        };

    }


}
