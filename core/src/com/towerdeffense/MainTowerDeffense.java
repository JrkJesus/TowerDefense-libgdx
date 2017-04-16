package com.towerdeffense;

import com.util.Tuple;
import com.util.XMLReader;
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
		Integer[] array = new Integer[]{5, 4, 8, 6, 3, 1, 2, 7, 10};
		Arrays.sort(array, Collections.reverseOrder());
		for(int number : array){
			System.out.println(number);
		}

		Integer[] better = Arrays.copyOf(array, 3);


		System.out.println();
		for(int number : better){
			System.out.println(number);
		}
//		this.setScreen(new PantallaPrincipal(this));
//		this.setScreen(new PantallaJuego(this));
		app.exit();
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
