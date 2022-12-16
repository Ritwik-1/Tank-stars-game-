package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

public class Missile extends General_Body{
    private float x;
    private float y;

    private CircleShape circle;
//    private Body body;
//    private BodyDef bodyDef;
//    private FixtureDef fixtureDef;
//    private Fixture fixture;
//    private float density = 0.4f;
//    private float friction = 0.5f;
//    private float restitution = 0.6f;

    public Missile(GameScreen g,float pos_x, float pos_y){
        super();
        this.x = pos_x;
        this.y = pos_y;
        bodyDef.type = BodyDef.BodyType.DynamicBody;;
        bodyDef.position.set(pos_x,pos_y);
        circle = new CircleShape();
        circle.setRadius(5);
        fixtureDef.shape = circle;
        body = g.getWorld().createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);

//        bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        fixtureDef = new FixtureDef();
//        fixtureDef.restitution = restitution;
//        fixtureDef.friction = friction;
//        fixtureDef.density = density;
//
//        fixture = body.createFixture(fixtureDef);
    }

//    public void setDensity(float d){
//        this.density = d;
//    }
//    public void setFriction(float f){
//        this.friction = f;
//    }
//    public void setRestitution(float r){
//        this.restitution = r;
//    }
//    public Body getBody() {
//        return body;
//    }
//
//    public float getFriction() {
//        return friction;
//    }
//
//    public float getRestitution() {
//        return restitution;
//    }
//
//    public float getDensity() {
//        return density;
//    }
}
