package com.dozerax.nuclearbullet.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dozerax.nuclearbullet.Screens.Nuclear;
import com.dozerax.nuclearbullet.Screens.NuclearBulletGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Nuclear bullet";
		config.width = NuclearBulletGame.WIDTH;
		config.height = NuclearBulletGame.HEIGHT;
		new LwjglApplication(new Nuclear(), config);
	}
}
