package com.mygdx.game.screens;


import com.mygdx.game.Game_Classes.Game_Player;
import com.mygdx.game.Game_Classes.Game_start;
import com.mygdx.game.Game_Classes.Missile;
import com.mygdx.game.TankStars;
import com.sun.net.httpserver.Authenticator;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

public class TestsScreen {

    class InnerTest{
        @Test
        public void testScreen(){
            Missile m = new Missile(new GameScreen(new TankStars(),new Game_start(new Game_Player() ,new Game_Player())),10,10);
            assertEquals(null,m);
        }

    }
    public static void main(String[] args){
        Result result = (Result) JUnitCore.runClasses(TestsScreen.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
