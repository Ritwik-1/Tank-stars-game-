package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.mygdx.game.screens.GameScreen;

public class Tank extends General_Body{
    private float x;
    private float y;

    private Texture Tank_Player;

    private CircleShape circle;

    private Sprite boxsprite1 = new Sprite(new Texture("Tank1.png"));
    private Sprite boxsprite2 = new Sprite(new Texture("Tank2_1.png"));
    private Sprite boxsprite3 = new Sprite(new Texture("Tank3.png"));

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

//        if(Tank_number == 1){
//            boxsprite1.setSize(100,100);
//            boxsprite1.setOrigin(boxsprite1.getHeight()/2,boxsprite1.getWidth()/2);
//            this.getBody().setUserData(boxsprite1);
//        }
//        else if(Tank_number == 2){
//            boxsprite2.setSize(100,100);
//            boxsprite2.setOrigin(boxsprite2.getHeight()/2,boxsprite2.getWidth()/2);
//            this.getBody().setUserData(boxsprite2);
//        }
//        else if (Tank_number == 3) {
//            boxsprite2.setSize(100,100);
//            boxsprite2.setOrigin(boxsprite2.getHeight()/2,boxsprite2.getWidth()/2);
//            this.getBody().setUserData(boxsprite2);
//        }


    }

}
