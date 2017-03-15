package com.Vistas;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaBase implements Screen
{
    protected final MainTowerDeffense mtd;
    OrthographicCamera cam;

    public PantallaBase(MainTowerDeffense _mtd)
    {
        mtd = _mtd;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
    }

    protected boolean isButtonPress(Texture btn, int x, int y) {

        return ( Gdx.input.getX() >  x && Gdx.input.getX() <  x + btn.getWidth())
                && (Gdx.graphics.getHeight() - Gdx.input.getY() > y
                    && Gdx.graphics.getHeight() - Gdx.input.getY() <  y + btn.getHeight());
    }

    protected void scaleFont(BitmapFont font, float scaleX, float scaleY){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scaleX, scaleY);
    }

    protected void scaleFont(BitmapFont font, float scale){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scale);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
