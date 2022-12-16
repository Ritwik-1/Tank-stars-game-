package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screens.GameScreen;

public class Game_Player {
    private String name;

    private Tank player_tank;

    private float player_health;

//    MORE ATTRIBUTES TO BE ADDED

    public Game_Player(Tank t){
        this.player_tank = t;
//        code to be written
    }

    public void setTank(int tankNUmber){
//        code to be written
    }

    public void fireMissile(GameScreen g){
        Missile missile = new Missile(g,this.player_tank.getBody().getPosition().x+4,this.player_tank.getBody().getPosition().y+4);
        missile.getBody().setLinearVelocity(new Vector2(200,300));
    }
}
