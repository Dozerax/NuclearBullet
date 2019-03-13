package com.dozerax.nuclearbullet.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dozerax.nuclearbullet.Resourses.Resourses;

public class GameOverScreen implements Screen {
    final Nuclear game;
    OrthographicCamera camera;

    public GameOverScreen(Nuclear game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(Resourses.backgoundGameOver,0,0,800,480);
        game.font.draw(game.batch,"Your score: "+ NuclearBulletGame.getScore(),370,NuclearBulletGame.HEIGHT/2);
        game.font.draw(game.batch,"Created by Danilovich Roman",600,20);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new NuclearBulletGame(game));
            NuclearBulletGame.setScore(0);
            dispose();
        }
    }

    @Override
    public void show() {

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

    }
}