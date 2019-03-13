package com.dozerax.nuclearbullet.Resourses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resourses {
    public static final Texture redplane = new Texture("red_plane.png");
    public static final Texture greenplane = new Texture("green_plane.png");
    public static final Texture blueplane = new Texture("blue_plane.png");
    public static final Texture greyplane = new Texture("grey_plane.png");
    public static final Texture backgound = new Texture("background2.png");
    public static final Texture nuclearbullet = new Texture("nuclearbullet.png");
    public static final Texture backgroundMenu = new Texture("menuBackground.png");
    public static final Texture backgoundGameOver = new Texture("backgoundGameOver.png");
    public static final Texture planeAnim = new Texture("plane.png");

    public static final Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("shootSound.mp3"));
    public static final Music backgoundMusic = Gdx.audio.newMusic(Gdx.files.internal("BitWeapon.mp3"));
}
