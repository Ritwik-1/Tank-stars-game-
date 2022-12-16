package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.mygdx.game.screens.GameScreen;

public class Tank extends General_Body{
    private float x;
    private float y;

    private Texture Tank_Player;

    private CircleShape circle;

    // TANK NO. SPECIFIES THE TANK WHICH THE PLAYER CHOOSES
    // WILL ADD THE IMAGE ACCORDINGLY

    public Tank(GameScreen g, float pos_x, float pos_y,int Tank_number){
        super();
        this.x = pos_x;
        this.y = pos_y;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos_x, pos_y);
        circle = new CircleShape();
        circle.setRadius(20);
        fixtureDef.shape = circle;
        body = g.getWorld().createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);
    }

}
