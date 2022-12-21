package com.mygdx.game.Game_Classes;

import java.io.Serializable;

public class Game_start implements Serializable {
    private Game_Player player1;
    private Game_Player player2;

    public Game_start(Game_Player p1,Game_Player p2){
        this.player1 = p1;
        this.player2 = p2;
    }

    public Game_Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Game_Player player1) {
        this.player1 = player1;
    }

    public Game_Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Game_Player player2) {
        this.player2 = player2;
    }
}
