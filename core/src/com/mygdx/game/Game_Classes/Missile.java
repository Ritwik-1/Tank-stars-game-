package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

public class Missile extends General_Body{

    private float x;
    private float y;

    private CircleShape circle;
    private Sprite MissileSprite1;
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
        MissileSprite1= new Sprite(new Texture("Missile2.png"));
        MissileSprite1.setOrigin(MissileSprite1.getHeight()/2,MissileSprite1.getWidth()/2);
        MissileSprite1.setSize(30,30);
        this.getBody().setUserData(MissileSprite1);

    }

    public void setMissilePos(float x,float y){
        this.x = x;
        this.y = y;
    }

}
