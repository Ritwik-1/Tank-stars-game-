package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Tank extends General_Body{
    private float x;
    private float y;

    private Texture Tank_Player;

    private Sprite tank1Sprite;
    private Sprite tank2Sprite;

    private Sprite tank3Sprite;

    private Collection sprites = new ArrayList<Sprite>();


    private CircleShape circle;


    // TANK NO. SPECIFIES THE TANK WHICH THE PLAYER CHOOSES
    // WILL ADD THE IMAGE ACCORDINGLY

    public Tank(GameScreen g, float pos_x, float pos_y,int Tank_number){
        this.x = pos_x;
        this.y = pos_y;
        sprites.add(tank1Sprite);
        sprites.add(tank2Sprite);
        sprites.add(tank3Sprite);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos_x, pos_y);
        body = g.getWorld().createBody(bodyDef);
        circle = new CircleShape();
        circle.setRadius(15);
        fixtureDef.shape = circle;
        fixture = body.createFixture(fixtureDef);



        if(Tank_number == 1){
            Iterator iter = sprites.iterator();
            tank1Sprite = (Sprite) iter.next();
            tank1Sprite = new Sprite(new Texture("GameScreenTank1.png"));
            tank1Sprite.setOrigin(tank1Sprite.getHeight()/2,tank1Sprite.getWidth()/2);
            tank1Sprite.setSize(80,52);
            this.getBody().setUserData(tank1Sprite);


        }
        else if(Tank_number == 2){
            Iterator iter = sprites.iterator();
            Sprite a = (Sprite) iter.next();
            tank2Sprite = (Sprite)iter.next();

            tank2Sprite = new Sprite(new Texture("GameScreenTank2_1.png"));
            tank2Sprite.setOrigin(tank2Sprite.getHeight()/2,tank2Sprite.getWidth()/2);
            tank2Sprite.setSize(100,90);
            this.getBody().setUserData(tank2Sprite);
        }
        else if (Tank_number == 3) {
            Iterator iter = sprites.iterator();
            Sprite a = (Sprite) iter.next();
            Sprite b = (Sprite) iter.next();
            tank3Sprite = (Sprite)iter.next();

            tank3Sprite = new Sprite(new Texture("GameScreenTank3.png"));
            tank3Sprite.setOrigin(tank3Sprite.getHeight()/2,tank3Sprite.getWidth()/2);
            tank3Sprite.setSize(80,52);
            this.getBody().setUserData(tank3Sprite);
        }
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }


}
