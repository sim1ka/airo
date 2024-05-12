package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.SCR_HEIGHT;
import static com.aerohokey.game.Aerohockey.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenAbout implements Screen {
    Aerohockey main;

    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;

    BitmapFont fontLarge, fontSmall;
    Texture imgBackGround;

    AeroButton btnBack;

    public ScreenAbout (Aerohockey main) {
        this.main = main;
        camera = main.cameraFont;
        touch = main.touch;
        batch = main.batch;
        fontLarge = main.fontLarge;
        fontSmall = main.fontSmall;

        imgBackGround = new Texture("bg2.png");

        btnBack = new AeroButton("Back", 200, fontLarge);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касания
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnBack.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }
        }

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        fontLarge.draw(batch, "Эта игра была сделана как проэкт для школы самсунг", 5,600);
        fontLarge.draw(batch, btnBack.text, btnBack.x, btnBack.y);
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

    }
}
