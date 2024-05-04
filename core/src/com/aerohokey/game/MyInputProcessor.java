package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.WORLD_WIDTH;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public class MyInputProcessor implements InputProcessor {
    Aerohockey main;
    World world;
    OrthographicCamera camera;
    Vector3 touch;
    KinematicBody bat0, bat1;

    public MyInputProcessor(Aerohockey main) {
        this.main = main;
        world = main.world;
        camera = main.camera;
        touch = main.touch;
        bat0 = main.screenGame.bat0;
        bat1 = main.screenGame.bat1;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenY, 0);
        camera.unproject(touch);
        if(touch.x < WORLD_WIDTH/2) {
            if(bat0.hit(touch.x, touch.y)){
                bat0.isDragged = true;
                bat0.oldX = bat0.getBody().getPosition().x;
                bat0.oldY = bat0.getBody().getPosition().y;
                System.out.println(bat0.isDragged );
            }
            if(bat1.hit(touch.x, touch.y)){
                bat1.isDragged = true;
                bat1.oldX = bat1.getBody().getPosition().x;
                bat1.oldY = bat1.getBody().getPosition().y;
                System.out.println(bat1.isDragged );
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenY, 0);
        camera.unproject(touch);
        if(touch.x < WORLD_WIDTH/2) {
            if(bat0.hit(touch.x, touch.y)){
                bat0.isDragged = false;
            }
            if(bat1.hit(touch.x, touch.y)){
                bat1.isDragged = false;
            }
        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, screenY, 0);
        camera.unproject(touch);
        if(bat0.isDragged){
            if(touch.x < WORLD_WIDTH/2) {
                bat0.oldX = bat0.getBody().getPosition().x;
                bat0.oldY = bat0.getBody().getPosition().y;
                bat0.getBody().setTransform(touch.x, touch.y, 0);
            }
            if(touch.x < WORLD_WIDTH/2) {
                bat1.oldX = bat1.getBody().getPosition().x;
                bat1.oldY = bat1.getBody().getPosition().y;
                bat1.getBody().setTransform(touch.x, touch.y, 0);
            }
        }
        return false;

    }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
