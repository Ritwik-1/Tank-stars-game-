package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

public class General_Body {
    protected Body body;
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected Fixture fixture;
    protected float density = 0.4f;
    protected float friction = 0.5f;
    protected float restitution = 0.6f;

    public General_Body(){
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
        fixtureDef.density = density;
    }

    public void setDensity(float d){
        this.density = d;
    }
    public void setFriction(float f){
        this.friction = f;
    }
    public void setRestitution(float r){
        this.restitution = r;
    }
    public Body getBody() {
        return body;
    }

    public float getFriction() {
        return friction;
    }

    public float getRestitution() {
        return restitution;
    }

    public float getDensity() {
        return density;
    }

    public BodyDef getBodyDef(){
        return bodyDef;
    }
}
