package com.dozerax.nuclearbullet.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dozerax.nuclearbullet.Screens.Nuclear;
import com.dozerax.nuclearbullet.Screens.NuclearBulletGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(NuclearBulletGame.WIDTH, NuclearBulletGame.HEIGHT);
        }
        @Override
        public ApplicationListener createApplicationListener() {
                return new Nuclear();
        }
}