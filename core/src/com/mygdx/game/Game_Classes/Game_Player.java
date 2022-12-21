package com.mygdx.game.Game_Classes;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screens.GameScreen;

public class Game_Player {
    private String name;

    private Tank player_tank;

    private float player_health = 30;

//    MORE ATTRIBUTES TO BE ADDED

    public Game_Player(){
    }

    public void setTank(Tank tank){
        this.player_tank = tank;
    }

    public void fireMissile(Missile missile,float vx,float vy){
//        System.out.println("Player tanks x :"+player_tank.getX());
//        System.out.println("Player tanks y :"+player_tank.getY());
//        missile.setMissilePos(player_tank.getX()+10,player_tank.getY()+1000);
        missile.getBody().setLinearVelocity(new Vector2(vx,vy));
    }

    public Tank getPlayer_tank(){
        return player_tank;
    }
}
