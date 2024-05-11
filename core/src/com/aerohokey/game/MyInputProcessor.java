package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
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
        //touch.set(screenX, screenY, 0);
        //camera.unproject(touch);
        if(pointer == 0) {
            touch.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
            camera.unproject(touch);
            if (touch.x < WORLD_WIDTH / 2) {
                if (bat0.hit(touch.x, touch.y)) {
                    bat0.isDragged = true;
                    bat0.pointer = 0;
                    bat0.oldX = bat0.getBody().getPosition().x;
                    bat0.oldY = bat0.getBody().getPosition().y;
                }
            } else if (touch.x >= WORLD_WIDTH / 2) {
                if (bat1.hit(touch.x, touch.y)) {
                    bat1.isDragged = true;
                    bat1.pointer = 0;
                    bat1.oldX = bat1.getBody().getPosition().x;
                    bat1.oldY = bat1.getBody().getPosition().y;
                }
            }
        } else if(pointer == 1){
            touch.set(Gdx.input.getX(1), Gdx.input.getY(1), 0);
            camera.unproject(touch);
            if (touch.x < WORLD_WIDTH / 2) {
                if (bat0.hit(touch.x, touch.y)) {
                    bat0.isDragged = true;
                    bat0.pointer = 1;
                    bat0.oldX = bat0.getBody().getPosition().x;
                    bat0.oldY = bat0.getBody().getPosition().y;
                }
            } else if (touch.x >= WORLD_WIDTH / 2) {
                if (bat1.hit(touch.x, touch.y)) {
                    bat1.isDragged = true;
                    bat1.pointer = 1;
                    bat1.oldX = bat1.getBody().getPosition().x;
                    bat1.oldY = bat1.getBody().getPosition().y;
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer == bat0.pointer && bat0.isDragged) {
            bat0.isDragged = false;
        } else if(pointer == bat1.pointer && bat1.isDragged) {
            bat1.isDragged = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        if(pointer == bat0.pointer && bat0.isDragged) {
            bat0.isDragged = false;
        } else if(pointer == bat1.pointer && bat1.isDragged) {
            bat1.isDragged = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (bat0.isDragged) {
            touch.set(Gdx.input.getX(bat0.pointer), Gdx.input.getY(bat0.pointer), 0);
            camera.unproject(touch);
            if(touch.x > WORLD_WIDTH/2) {
                bat0.isDragged = false;
                return true;
            }
            bat0.oldX = bat0.getBody().getPosition().x;
            bat0.oldY = bat0.getBody().getPosition().y;
            bat0.getBody().setTransform(touch.x, touch.y, 0);
        }
        if(bat1.isDragged) {
            touch.set(Gdx.input.getX(bat1.pointer), Gdx.input.getY(bat1.pointer), 0);
            camera.unproject(touch);
            if(touch.x <= WORLD_WIDTH/2) {
                bat1.isDragged = false;
                return true;
            }
            bat1.oldX = bat1.getBody().getPosition().x;
            bat1.oldY = bat1.getBody().getPosition().y;
            bat1.getBody().setTransform(touch.x, touch.y, 0);
        }
        return true;
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
