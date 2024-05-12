package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.SCR_WIDTH;
import static com.aerohokey.game.Aerohockey.WORLD_HEIGHT;
import static com.aerohokey.game.Aerohockey.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    Aerohockey main;

    SpriteBatch batch, batchFont;
    OrthographicCamera camera, cameraFont;
    Vector3 touch;
    World world;
    Box2DDebugRenderer renderer;

    BitmapFont font;
    Texture imgBackGround;
    Texture imgPuck;
    Texture imgBat0, imgBat1;
    Texture imgExit;

    AeroButton btnExit;

    StaticBody wallLeft0, wallLeft1;
    StaticBody wallRight0, wallRight1;
    StaticBody wallTop, wallDown;
    KinematicBody bat0, bat1;
    DynamicBody puck;

    private int score0, score1;
    private static final float SCORE_POSITION_X = 3.5f;
    private static final float SCORE_POSITION_Y = 9f;
    private static final float EXIT_SIZE = 0.5f;

    public ScreenGame (Aerohockey main){
        this.main = main;

        camera = main.camera;
        cameraFont = main.cameraFont;
        touch = main.touch;
        batch = main.batch;
        batchFont = new SpriteBatch();
        world = main.world;
        renderer = main.renderer;
        //font = new BitmapFont(Gdx.files.internal("vis.fnt"));
        font = main.fontLarge;
        imgBackGround = new Texture("pole.png");
        imgPuck = new Texture("Puck.png");
        imgBat0 = new Texture("bita1.png");
        imgBat1 = new Texture("bita2.png");
        imgExit = new Texture("exit.png");

        btnExit = new AeroButton(imgExit, WORLD_WIDTH-EXIT_SIZE, WORLD_HEIGHT-EXIT_SIZE, EXIT_SIZE, EXIT_SIZE);

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

    public void addGoal(int goal) {
        if(goal == 0) score0++;
        else score1++;
        restart();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new MyInputProcessor(main));
    }

    @Override
    public void render(float delta) {
        // касания
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnExit.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }
        }

        // события
        if(puck.getBody().getPosition().x < -puck.getWidth()){
            addGoal(1);
        }
        if(puck.getBody().getPosition().x > WORLD_WIDTH+puck.getWidth()){
            addGoal(0);
        }

        //отрсовка
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        renderer.render(world, camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(imgBat0, bat0.getX(), bat0.getY(), bat0.getHeight(), bat0.getWidth());
        batch.draw(imgBat1, bat1.getX(), bat1.getY(), bat1.getHeight(), bat1.getWidth());
        batch.draw(imgPuck, puck.getX(), puck.getY(), puck.getHeight()/2, puck.getWidth()/2,
                puck.getHeight(), puck.getWidth(), 1, 1,puck.getAngle(), 0, 0, 130, 128, false, false);

        batch.draw(btnExit.img, btnExit.x, btnExit.y, btnExit.width, btnExit.height);
        batch.end();
        batchFont.setProjectionMatrix(cameraFont.combined);
        batchFont.begin();
        font.draw(batchFont, ":", SCR_WIDTH/2-6, 880);
        font.draw(batchFont, ""+score0, 0, 880, SCR_WIDTH/2-20, Align.right, true);
        font.draw(batchFont, ""+score1, SCR_WIDTH/2+20, 880, SCR_WIDTH/2-20, Align.left, true);
        batchFont.end();
        world.step(1/60f, 6, 2);
    }

    private void restart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
