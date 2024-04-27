package com.aerohokey.game;

import static com.aerohokey.game.Aerohokey.SCR_HEIGHT;
import static com.aerohokey.game.Aerohokey.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class ScreenGame implements Screen {
    Aerohokey aerohokey;

    Texture imgBackGround;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;
    AeroButton btnExit;
    Texture imgPuck;
    World world;
    Box2DDebugRenderer debugRenderer;
    StaticBody floor;
    StaticBody wallLeft, wallRight;
    Array<DynamicBody> balls = new Array<>();

    public ScreenGame (Aerohokey aerohokey){
        this.aerohokey = aerohokey;
        imgBackGround = new Texture("pole.jpg");
        imgPuck = new Texture("puck.png");
        camera = aerohokey.camera;
        touch = aerohokey.touch;
        batch = aerohokey.batch;
        font = aerohokey.fontSmall;

       btnExit = new AeroButton("exit", 0,1590, font);
        floor = new StaticBody(world, 8, 0.5f, 16, 1);
        wallLeft = new StaticBody(world, 0.5f, 5, 1, 8);
        wallRight = new StaticBody(world, 15.5f, 5, 1, 8);
    }






    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnExit.hit(touch.x, touch.y)) {
                aerohokey.setScreen(aerohokey.screenMenu);
            }
        }



        //отрсовка
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
        batch.end();
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
