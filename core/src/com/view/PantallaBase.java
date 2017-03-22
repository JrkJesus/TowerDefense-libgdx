package com.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    protected final MainTowerDeffense mtd;
    protected final Skin skin;
    protected final Stage stage;
    protected final TextureAtlas atlas;
    protected final BitmapFont font;


    public PantallaBase(MainTowerDeffense _mtd)
    {
        mtd = _mtd;
        background = new Texture(Gdx.files.internal("Buttons\\background.png"));
        font = new BitmapFont(Gdx.files.internal("GUI\\font-export.fnt"));
        skin = new Skin(Gdx.files.internal("GUI\\quantum-horizon-ui.json"));
        atlas = new TextureAtlas(Gdx.files.internal("GUI\\quantum-horizon-ui.atlas"));
        skin.addRegions(atlas);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    protected void scaleFont(BitmapFont font, float scale){

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(scale);
    }
    
    protected void addButton(Button btn, int x, int y, int width, int height){
        btn.setWidth(width);
        btn.setHeight(height);
        btn.setPosition(x, y);
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
        skin.dispose();
        stage.dispose();
        background.dispose();
    }
}
