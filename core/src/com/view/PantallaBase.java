package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaBase implements Screen
{
    protected Texture background;
    public final MainTowerDeffense mtd;
    protected final Skin skin;
    protected final Stage stage;
    protected final TextureAtlas atlas;
    protected final BitmapFont font;
    public final int width, height;
    public Music mainMenuMusic;

    public PantallaBase(MainTowerDeffense _mtd)
    {
        mtd = _mtd;
        background = new Texture(Gdx.files.internal("Buttons\\background.png"));
        font = new BitmapFont(Gdx.files.internal("GUI\\font-title-export.fnt"), Gdx.files.internal("GUI\\font-title-export.png"), false);
        font.setColor(Color.GRAY);
        skin = new Skin(Gdx.files.internal("GUI\\quantum-horizon-ui.json"));
        atlas = new TextureAtlas(Gdx.files.internal("GUI\\quantum-horizon-ui.atlas"));
        skin.addRegions(atlas);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Loop-Menu.wav"));
    }

    protected void scaleFont(BitmapFont font, float scale){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scale);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0,0,0,1);
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
        dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        background.dispose();
        mainMenuMusic.stop();
        mainMenuMusic.dispose();
    }
}
