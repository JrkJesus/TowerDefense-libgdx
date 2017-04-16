package com.towerdeffense;

import com.util.Tuple;
import com.util.XMLReader;
import com.view.PantallaClasificacion;
import com.view.PantallaJuego;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.view.PantallaPrincipal;

import java.util.Arrays;
import java.util.Collections;

import static com.badlogic.gdx.Gdx.app;

public class MainTowerDeffense extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
//		this.setScreen(new PantallaPrincipal(this));
//		this.setScreen(new PantallaJuego(this));
		this.setScreen(new PantallaClasificacion(this));
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
