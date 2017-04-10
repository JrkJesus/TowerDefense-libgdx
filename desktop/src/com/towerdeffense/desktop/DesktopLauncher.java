package com.towerdeffense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.towerdeffense.MainTowerDeffense;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1080;
		config.width = 1920;
//		config.height = 720;
//		config.width = 1280;
		config.fullscreen = false;
		new LwjglApplication(new MainTowerDeffense(), config);
	}
}
