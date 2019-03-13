package com.dozerax.nuclearbullet.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.dozerax.nuclearbullet.Resourses.Resourses;
import com.dozerax.nuclearbullet.enums.EnemyType;
import com.dozerax.nuclearbullet.entities.Bullet;
import com.dozerax.nuclearbullet.entities.Enemy;
import com.dozerax.nuclearbullet.entities.Player;

import java.util.Iterator;

public class NuclearBulletGame implements Screen {
	final Nuclear game;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	SpriteBatch batch;
	Texture img;
	Music music = Resourses.backgoundMusic;

	private static final int FRAME_COLS = 8;
	private static final int FRAME_ROWS = 1;
	Animation flyAnimation;
	Texture flySheet;
	TextureRegion[] flyFrames;
	TextureRegion currentFrame;
	float stateTime;

	OrthographicCamera camera;

	private float backgoundX = -5;

	public Array<Bullet> bullets;
	public Array<Enemy> enemies;

	long lastBullet;
	long lastEnemy;

	private Player player;
	private Enemy enemy;

	private Rectangle bullet;
	private Rectangle enemys;

	private boolean gameOver = false;
	private boolean spawnBullen = true;
	private boolean spawnEnemy = true;

	private static int score;
	private int destroyEnemy;

	public NuclearBulletGame(Nuclear games) {
		this.game = games;
		init();
		bullets = new Array<Bullet>();
		enemies = new Array<Enemy>();
		spawnBullet();
		spawnEnemy();
		createAnimPlayer();
	}

	public void init() {
		player = new Player();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		moveBackground(true);
		updatePlayer();
		printEnemy();
		printBullet();
		moveBulletEnemy();
		renderPlayer();
		game.font.draw(game.batch, "Score: " + getScore(), WIDTH / 2, 20);
		game.batch.end();
	}

	private void moveBackground(boolean moveBackground) {
		game.batch.draw(Resourses.backgound, backgoundX, -5);
		if (moveBackground == true) {
			backgoundX -= 2;
			if (backgoundX <= -3950) {
				backgoundX = -5;
			}
		}
	}

	//отрисовка, движение самолета, проверка колизизи с границами экрана начало
	public void createAnimPlayer() {
		flySheet = Resourses.planeAnim;
		TextureRegion[][] textureRegions = TextureRegion.split(flySheet, flySheet.getWidth() / FRAME_COLS, flySheet.getHeight() / FRAME_ROWS);
		flyFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				flyFrames[index++] = textureRegions[i][j];
			}
		}
		flyAnimation = new Animation(0.100f, flyFrames);
		batch = new SpriteBatch();
		stateTime = 0f;
	}

	public void renderPlayer() {
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = (TextureRegion) flyAnimation.getKeyFrame(stateTime, true);
		game.batch.draw(currentFrame, player.x, player.y);
	}

	private void updatePlayer() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.moveUp();

		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.moveDown();

		}
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			player.y = touchPos.y;
		}
		player.Colizia();
	}

	//отрисовка, движение самолета, проверка колизизи с границами экрана конец
	//создание вргов, движение и отрисовка начало
	public void spawnEnemy() {
		if (spawnEnemy == true) {
			EnemyType enemyType;
			int i = MathUtils.random(1, 100);
			int yRand = MathUtils.random(100, NuclearBulletGame.HEIGHT - 100);

			if (i > 0 && i <= 50) {
				enemyType = EnemyType.BLUEPLANE;
			} else if (i > 50 && i <= 90) {
				enemyType = EnemyType.GREENPLANE;
			} else {
				enemyType = EnemyType.REDPLANE;
			}
			enemy = new Enemy(NuclearBulletGame.WIDTH, yRand, enemyType, game);
			enemies.add(enemy);
			lastEnemy = TimeUtils.nanoTime();
		}
	}

	public void printEnemy() {
		if (spawnEnemy == true) {
			for (Enemy enemy : enemies) {
				game.batch.draw(enemy.getTexture(), enemy.x, enemy.y);
			}
		}
	}

	//создание врагов, движение и отрисовка конец
	//создание пули, движение и отрисовка начало
	public void spawnBullet() {
		if (spawnBullen == true) {
			Bullet bullet = new Bullet(player.x + player.getTexture().getWidth() / 2 - player.getTexture().getWidth() / 6, player.y, game);
			bullets.add(bullet);
			lastBullet = TimeUtils.nanoTime();
		}
	}

	public void printBullet() {
		if (spawnBullen == true) {
			for (Rectangle bullet : bullets) {
				game.batch.draw(Resourses.nuclearbullet, bullet.x, bullet.y);
			}
		}
	}

	public void moveBulletEnemy() {
		if (spawnBullen == true && spawnEnemy == true) {
			Iterator<Bullet> iterator = bullets.iterator();

			while (iterator.hasNext()) {
				bullet = iterator.next();
				((Bullet) bullet).move();
				if (bullet.x > WIDTH) {
					iterator.remove();
				}
			}

			if (TimeUtils.nanoTime() - lastBullet > 400000000) {
				spawnBullet();
			}

			Iterator<Enemy> iter = enemies.iterator();
			while (iter.hasNext()) {
				enemys = iter.next();
				((Enemy) enemys).move();
				if (enemys.x < -103) {
					iter.remove();
				}
				if (bullet.overlaps(enemys)) {
					destroyEnemy++;
					setScore(destroyEnemy);
					iter.remove();
					iterator.remove();
				}
				if (enemys.overlaps(player)) {
					gameOver = true;
					gameOver();
					music.stop();
				}
				if (enemys.x <= -103) {
					gameOver = true;
					gameOver();
					music.stop();
				}
			}
			if (TimeUtils.nanoTime() - lastEnemy > 1000000000) {
				spawnEnemy();
			}
		}
	}
	//создание пули, движение и отрисовка конец

	public void gameOver() {
		if (gameOver == true) {
			game.setScreen(new GameOverScreen(game));
		}
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int s) {
		score = s;
	}

	@Override
	public void show() {
		music.play();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
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
}