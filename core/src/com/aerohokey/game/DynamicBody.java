package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.TYPE_BRICK;
import static com.aerohokey.game.Aerohockey.TYPE_CIRCLE;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicBody {
    public float x, y;

    private float r;
    private float width, height;
    private Body body;
    private Fixture fixture;

    DynamicBody(World world, float x, float y, float r, String o) {
        this.x = x;
        this.y = y;
        this.r = r;
        width = height = r * 2;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 0.1f;
        bodyDef.angularDamping = 0.1f;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
        body.setUserData(o);

        CircleShape shape = new CircleShape();
        shape.setRadius(r);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.2f; // плотность
        fixtureDef.friction = 0.4f; // трение
        fixtureDef.restitution = 0.8f; // упругость

        fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    DynamicBody(World world, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 0.1f;
        bodyDef.angularDamping = 0.1f;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.2f; // плотность
        fixtureDef.friction = 0.4f; // трение
        fixtureDef.restitution = 0.9f; // упругость

        fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public float getX() {
        return body.getPosition().x - width / 2;
    }

    public float getY() {
        return body.getPosition().y - height / 2;
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

    public void setImpulse(Vector2 v) {
        body.applyLinearImpulse(v, body.getPosition(), true);
    }

    public void resetPosition(float x, float y) {
        body.setTransform(x, y, 0);
        body.setLinearVelocity(0,0);
        body.setAngularVelocity(0);
    }

    public void update(float delta) {
    }
}


