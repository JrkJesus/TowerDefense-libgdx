package com.vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaBase implements Screen
{
    protected final MainTowerDeffense mtd;
    protected OrthographicCamera cam;
    protected Viewport view;


    public PantallaBase(MainTowerDeffense _mtd)
    {
        mtd = _mtd;
        // cam = new OrthographicCamera(1366, 768);
        cam = new OrthographicCamera(1280, 720);
        cam.setToOrtho(false, 1280, 720); // Asigno una vista de mi mapa.
        cam.rotate(90); // Ponemos la vista en vertical.
        view = new FitViewport(1280, 720, cam);
    }

    protected boolean isButtonPress(Texture btn, int x, int y) {

        System.out.println(Gdx.input.getX()  +",  "+ Gdx.input.getY() + " --> "+ x +",  " + y);
        return ( Gdx.input.getX() >  x && Gdx.input.getX() <  x + btn.getWidth())
                && (Gdx.graphics.getHeight() - Gdx.input.getY() > y
                && Gdx.graphics.getHeight() - Gdx.input.getY() < y + btn.getHeight());
    }

    protected void scaleFont(BitmapFont font, float scaleX, float scaleY){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scaleX, scaleY);
    }

    protected void scaleFont(BitmapFont font, float scale){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scale);
    }
    
    protected void addButton(Texture btn, String text, int x, int y, int marginLeft, int marginBottom){
        mtd.batch.draw(btn, x, y);
        mtd.font.draw(mtd.batch, text, x + marginLeft, y + marginBottom);
    
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        // Vector2 size = Scaling.fit.apply(800, 480, width, height);
        // int viewportX = (int)(width - size.x) / 2;
        // int viewportY = (int)(height - size.y) / 2;
        // int viewportWidth = (int)size.x;
        // int viewportHeight = (int)size.y;
        // Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
        // stage.setViewport(800, 480, true, viewportX, viewportY, viewportWidth, viewportHeight);
        view.update(width, height);
        cam.update();
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
