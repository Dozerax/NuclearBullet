package com.dozerax.nuclearbullet.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dozerax.nuclearbullet.Screens.Nuclear;
import com.dozerax.nuclearbullet.enums.EnemyType;
import com.dozerax.nuclearbullet.Resourses.Resourses;

public class Enemy extends GameObject {
    private EnemyType enemyType;

    private Texture texture;

    private static int speedBlue = 200;
    private static int speedGreen = 250;
    private static int speedRed = 300;

    public Enemy(float x, float y,EnemyType enemyType, Nuclear game){
        texture = Resourses.blueplane;
        this.x = x;
        this.y = y;
        if (enemyType == EnemyType.REDPLANE){
            this.enemyType = enemyType;
            this.speed = speedRed;
            texture = Resourses.redplane;
        }
        if (enemyType == EnemyType.GREENPLANE){
            this.enemyType = enemyType;
            this.speed = speedGreen;
            texture = Resourses.greenplane;
        }
        if (enemyType == EnemyType.BLUEPLANE){
            this.enemyType = enemyType;
            this.speed = speedBlue;
            texture = Resourses.blueplane;
        }

        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public void move(){
        this.x -= speed * Gdx.graphics.getDeltaTime();
    }

    public Texture getTexture()
    {
        return texture;
    }

    public EnemyType getType()
    {
        return enemyType;
    }
}