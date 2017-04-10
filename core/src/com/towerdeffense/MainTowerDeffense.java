package com.towerdeffense;

import com.view.PantallaJuego;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainTowerDeffense extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

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
