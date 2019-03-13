package com.dozerax.nuclearbullet.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.dozerax.nuclearbullet.Screens.Nuclear;
import com.dozerax.nuclearbullet.Resourses.Resourses;

public class Bullet extends GameObject {

    private static Texture texture;
    private Sound sound;

    private static final float DAMAGE_PLAYER = 0.5f;

    private static int bulletSpeed;

    public Bullet(float x, float y, Nuclear game){
        sound = Resourses.shootSound;
        texture = Resourses.nuclearbullet;
        bulletSpeed = 700;

        this.x = x;
        this.y = y+3;

        this.width = texture.getWidth();
        this.height = texture.getHeight();
        sound.play(0.3f);
    }

    public void move(){
        this.x += bulletSpeed * Gdx.graphics.getDeltaTime();
    }

    public Texture getTexture(){ return texture;}
}