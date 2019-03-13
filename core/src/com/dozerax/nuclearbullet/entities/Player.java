package com.dozerax.nuclearbullet.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dozerax.nuclearbullet.Screens.NuclearBulletGame;
import com.dozerax.nuclearbullet.Resourses.Resourses;

public class Player extends GameObject {
    private int STARTING_X = 100;
    private int STARTING_Y = NuclearBulletGame.HEIGHT /2;

    public Texture texture;
    private int speed = 200;

    public Player(){
        texture = Resourses.greyplane;

        this.x = STARTING_X;
        this.y = STARTING_Y - texture.getHeight()/2;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void moveUp(){
        this.y += speed * Gdx.graphics.getDeltaTime();
    }
    public void moveDown(){
        this.y -= speed * Gdx.graphics.getDeltaTime();
    }
    public void Colizia(){
        if(this.y > NuclearBulletGame.HEIGHT -100){
            this.y = NuclearBulletGame.HEIGHT -100;
        }
        if (this.y < 100){
            this.y = 100;
        }
    }
    public Texture getTexture()
    {
        return texture;
    }
}