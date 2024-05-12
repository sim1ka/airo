package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.SCR_HEIGHT;
import static com.aerohokey.game.Aerohockey.SCR_WIDTH;
import static com.aerohokey.game.Aerohockey.WORLD_HEIGHT;
import static com.aerohokey.game.Aerohockey.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    Aerohockey main;

    SpriteBatch batch;
    OrthographicCamera camera, cameraForFont;
    Vector3 touch;
    World world;
    Box2DDebugRenderer renderer;

    BitmapFont font;
    Texture imgBackGround;
    Texture imgPuck;
    Texture imgBat0, imgBat1;

    AeroButton btnExit;

    StaticBody wallLeft0, wallLeft1;
    StaticBody wallRight0, wallRight1;
    StaticBody wallTop, wallDown;
    KinematicBody bat0, bat1;
    DynamicBody puck;

    private int score;
    private static final float FONT_SIZE = 0.1f;
    private static final float SCORE_POSITION_X = 3.5f;
    private static final float SCORE_POSITION_Y = 9f;


    public ScreenGame (Aerohockey main){
        this.main = main;

        camera = main.camera;
        cameraForFont = main.cameraForFont;
        touch = main.touch;
        batch = main.batch;
        world = main.world;
        renderer = main.renderer;
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(FONT_SIZE);
        //font = main.fontSmall;

        imgBackGround = new Texture("pole.png");
        imgPuck = new Texture("Puck.png");
        imgBat0 = new Texture("bita1.png");
        imgBat1 = new Texture("bita2.png");

        //btnExit = new AeroButton("exit", 0,1590, font);

        wallDown = new StaticBody(world, 8, 0.1f, 16, 0.2f);
        wallTop = new StaticBody(world, 8, 8.9f, 16, 0.2f);
        wallLeft0 = new StaticBody(world, 0.1f, 1.5f, 0.2f, 3);
        wallLeft1 = new StaticBody(world, 0.1f, 7.5f, 0.2f, 3);
        wallRight0 = new StaticBody(world, 15.9f, 1.5f, 0.2f, 3);
        wallRight1 = new StaticBody(world, 15.9f, 7.5f, 0.2f, 3);

        puck = new DynamicBody(world, 8, 4.5f, 0.6f, "puck");
        bat0 = new KinematicBody(world, 3, 4.5f, 0.6f, "bat0");
        bat1 = new KinematicBody(world, 13, 4.5f, 0.6f, "bat1");
    }
    public void addGoal() {
        score++;
        restart();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new MyInputProcessor(main));
    }

    @Override
    public void render(float delta) {
        /*if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnExit.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }
        }*/


        //отрсовка
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        renderer.render(world, camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        //font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
        batch.draw(imgBat0, bat0.getX(), bat0.getY(), bat0.getHeight(), bat0.getWidth());
        batch.draw(imgBat1, bat1.getX(), bat1.getY(), bat1.getHeight(), bat1.getWidth());
        batch.draw(imgPuck,  puck.getX(), puck.getY(), puck.getHeight(), puck.getWidth());
        font.draw(batch, "Score: " + score, SCORE_POSITION_X, SCORE_POSITION_Y);
        batch.end();
        world.step(1/60f, 6, 2);
    }

    private void restart() {
        puck.resetPosition(8.0f, 4.5f);
        bat0.resetPosition(3.0f, 4.5f);
        bat1.resetPosition(13.0f, 4.5f);
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
        imgBackGround.dispose();
    }
}
