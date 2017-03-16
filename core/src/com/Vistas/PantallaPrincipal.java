package com.Vistas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.towerdeffense.MainTowerDeffense;

/**
 * Created by skarg on 09/03/2017.
 */

public class PantallaPrincipal extends PantallaBase
{
    private Texture button;
    private static int MARGINBUTTONX = 35, MARGINBUTTONY = 55;
    private int width, height;

    public PantallaPrincipal(MainTowerDeffense m) {
        super(m);
        button = new Texture(Gdx.files.internal("Buttons\\RedButton-Bar.png"));
        witdh = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        mtd.batch.begin();
        
        scaleFont(mtd.font, 3);
        addButton(button, 150, height - 300, "Selecionar Juego", this.MARGINBUTTONX, this.MARGINBUTTONY);
        addButton(button, 150, height - 200, "Configuracion", this.MARGINBUTTONX, this.MARGINBUTTONY);
        addButton(button, 150, height - 100, "Salir", this.MARGINBUTTONX, this.MARGINBUTTONY)
       
        mtd.batch.end();

        if( Gdx.input.justTouched() && isButtonPress(button, 100, height - 300) ){
            mtd.setScreen(new PantallaJuego(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, 100, height - 200) ){
            mtd.setScreen(new PantallaConfiguracion(mtd));
        }
        if( Gdx.input.justTouched() && isButtonPress(button, 100, height - 100) ){
            Gdx.app.exit();
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        button.dispose();
    }
}
