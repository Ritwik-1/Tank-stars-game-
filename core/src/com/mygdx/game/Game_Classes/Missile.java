package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

public class Missile extends General_Body{

    private float x;
    private float y;

    private CircleShape circle;
//    private Body body;

    public Missile(GameScreen g,float x,float y){
        super();
        this.x = x;
        this.y = y;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.x = x;
        bodyDef.position.y = y;

        circle = new CircleShape();
        circle.setRadius(5);
        body = g.getWorld().createBody(bodyDef);
        fixtureDef.shape = circle;
        fixture = body.createFixture(fixtureDef);
    }

    public void setMissilePos(float x,float y){
        this.x = x;
        this.y = y;
    }

}
