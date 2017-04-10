package com.towerdeffense;

import com.Limpieza.Pantalla;
import com.util.Constants;
import com.view.PantallaJuego;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainTowerDeffense extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
//		this.setScreen(new PantallaPrincipal(this));
		this.setScreen(new PantallaJuego(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
