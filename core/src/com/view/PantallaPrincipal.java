package com.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.towerdeffense.MainTowerDeffense;
import com.util.Constants;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaPrincipal extends PantallaBase
{
//    buttonPressWidht, buttonPressHeight,
    private final int  width, height;

    private TextButton play, exit;
    private Button conf;
    private final TextButton.TextButtonStyle styleBtn;
    private final Button.ButtonStyle styleConf;

    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        styleBtn = new TextButton.TextButtonStyle();
        styleConf = new TextButton.TextButtonStyle();
    }

    @Override
    public void show() {
        super.show();

        styleBtn.font = font;
        styleBtn.up = skin.getDrawable("button");
        styleBtn.down = skin.getDrawable("button-pressed");
        styleConf.up = skin.getDrawable("touchpad");
        styleConf.down = skin.getDrawable("touchpad-pressed");

        play = new TextButton("Jugar", styleBtn);
        addButton(play, 3*width/8, height-height/5, width/4, 75);

        conf = new Button(styleConf);
        addButton(conf, width-60, height-60, 50, 50);

        exit = new TextButton("Salir", styleBtn);
        addButton(exit, 3*width/8, (height/15), width/4, 75);


        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                mtd.setScreen(new PantallaJuego(mtd));
            }
        });
        conf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                mtd.setScreen(new PantallaConfiguracion(mtd));
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });

        stage.addActor(play);
        stage.addActor(conf);
        stage.addActor(exit);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mtd.batch.begin();
        mtd.batch.draw(background, 0, 0, width, height);
        mtd.batch.end();

        stage.draw();
    }


    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }
}
