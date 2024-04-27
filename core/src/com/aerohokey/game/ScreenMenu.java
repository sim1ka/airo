package com.aerohokey.game;

import static com.aerohokey.game.Aerohokey.SCR_HEIGHT;
import static com.aerohokey.game.Aerohokey.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenMenu implements Screen {
    Aerohokey aerohokey;

    Texture imgBackGround;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;
    AeroButton btnPlay;
    AeroButton btnSettings;
    AeroButton btnAbout;
    AeroButton btnExit;

    public ScreenMenu (Aerohokey aerohokey) {
        this.aerohokey = aerohokey;
        imgBackGround = new Texture("bg2.png");
        camera = aerohokey.camera;
        touch = aerohokey.touch;
        batch = aerohokey.batch;
        font = aerohokey.fontSmall;


        btnPlay = new AeroButton("Play", 380, 1000, font);
        btnSettings = new AeroButton("Settings", 335, 880, font);
        btnAbout = new AeroButton("About Game", 290, 780, font);
        btnExit = new AeroButton("Exit", 380, 630, font);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnPlay.hit(touch.x, touch.y)){
                aerohokey.setScreen(aerohokey.screenGame);
            }
            if(btnSettings.hit(touch.x, touch.y)){
                aerohokey.setScreen(aerohokey.screenSettings);
            }
            if(btnAbout.hit(touch.x, touch.y)){
                aerohokey.setScreen(aerohokey.screenAbout);
            }
            if(btnExit.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
        }

        // события

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch, btnPlay.text, btnPlay.x, btnPlay.y);
        font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        font.draw(batch, btnAbout.text, btnAbout.x, btnAbout.y);
        font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
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
