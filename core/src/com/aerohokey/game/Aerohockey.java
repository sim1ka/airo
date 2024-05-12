package com.aerohokey.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Aerohockey extends Game {
	public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;
	public static final float SCR_WIDTH = 1600, SCR_HEIGHT = 900;
	public static final int TYPE_CIRCLE = 0, TYPE_BRICK = 1;


	SpriteBatch batch;
	OrthographicCamera camera, cameraFont;
	Vector3 touch;
	BitmapFont fontLarge, fontSmall;

	ScreenMenu screenMenu;
	ScreenSettings screenSettings;
	ScreenAbout screenAbout;
	ScreenGame screenGame;
	World world;
	Box2DDebugRenderer renderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		cameraFont = new OrthographicCamera();
		cameraFont.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		world = new World(new Vector2(0, 0), true);
		world.setContactListener(new MyContactListener(this));
		renderer = new Box2DDebugRenderer();
		renderer.setDrawVelocities(true);

		fontLarge = new BitmapFont(Gdx.files.internal("gagalin.fnt"));
		fontSmall = new BitmapFont(Gdx.files.internal("gagalin.fnt"));


		screenMenu = new ScreenMenu(this);
		screenSettings = new ScreenSettings(this);
		screenAbout = new ScreenAbout(this);
		screenGame = new ScreenGame(this);
		setScreen(screenMenu);
	}


	@Override
	public void dispose () {
		batch.dispose();
		fontLarge.dispose();
		fontSmall.dispose();
		world.dispose();
		renderer.dispose();
	}
}
