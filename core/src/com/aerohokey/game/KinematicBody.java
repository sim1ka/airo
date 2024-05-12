package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.WORLD_WIDTH;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class KinematicBody {
    public float x, y;
    public float oldX, oldY;
    private float r;
    private float width, height;
    private Body body;
    private Fixture fixture;
    boolean isDragged;
    int pointer;

    KinematicBody(World world, float x, float y, float r, String o){
        this.x = x;
        this.y = y;
        this.r = r;
        width = height = r*2;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
        body.setUserData(o);

        CircleShape shape = new CircleShape();
        shape.setRadius(r);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public float getX() {
        return body.getPosition().x-width/2;
    }

    public float getY() {
        return body.getPosition().y-height/2;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getAngle() {
        return body.getAngle() * MathUtils.radiansToDegrees;
    }

    public Body getBody() {
        return body;
    }

    public boolean hit(float tx, float ty) {
        return fixture.testPoint(tx, ty);
    }

    public Vector2 getImpulse() {
        return new Vector2((body.getPosition().x - oldX)*5, (body.getPosition().y - oldY)*5);
    }

    public void resetPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
    }
}
