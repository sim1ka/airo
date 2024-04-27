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

public class Aerohokey extends Game {
	public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;
	public static final float SCR_WIDTH = 900, SCR_HEIGHT = 1600;
	public static final int TYPE_SMILE = 0, TYPE_BRICK = 1;

	KinematicBody platform;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont fontLarge, fontSmall;

	ScreenMenu screenMenu;
	ScreenSettings screenSettings;
	ScreenAbout screenAbout;
	ScreenGame screenGame;
	World world;
	Box2DDebugRenderer debugRenderer;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		touch = new Vector3();
		fontLarge = new BitmapFont(Gdx.files.internal("gagalin.fnt"));
		fontSmall = new BitmapFont(Gdx.files.internal("gagalin.fnt"));
		world = new World(new Vector2(0f, 0f), true);
		world.setContactListener(new MyContactListener(this));
		debugRenderer = new Box2DDebugRenderer();
		debugRenderer.setDrawVelocities(true);



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
	}
}
