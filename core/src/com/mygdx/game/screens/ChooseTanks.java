package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;
import org.w3c.dom.css.Rect;
import  com.badlogic.gdx.scenes.scene2d.ui.Label;

import javax.swing.text.View;
import java.awt.*;
public class ChooseTanks implements Screen{
    private TankStars game;
    private Viewport gamePort;
    private Rectangle Tank1 = new Rectangle();
    private Rectangle Tank2 = new Rectangle();
    private Rectangle Tank3 = new Rectangle();

    private Stage stage;

    private BitmapFont font;

//    private Label l1;

    private Texture ImageText;
    private Texture background;
    private Texture Tank1Image;
    private Texture Tank2Image;
    private Texture Tank3Image;

    private OrthographicCamera gameCam;

    public ChooseTanks(TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);
    }

    @Override
    public void show() {
        stage = new Stage();

        gameCam = new OrthographicCamera();

        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        font = new BitmapFont();

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/cloud-form/skin/cloud-form-ui.json"));

//        l1 = new Label("CHOOSE TANK",s);
//
//        l1.setPosition(500,500);
//        l1.setSize(500,500);


//        stage.addActor(l1);
        background = new Texture(Gdx.files.internal("ChooseTankBG.jpg"));
        Tank1Image = new Texture(Gdx.files.internal("Tank1.png"));
        Tank2Image = new Texture(Gdx.files.internal("Tank2.png"));
        Tank3Image = new Texture(Gdx.files.internal("Tank3.png"));
        ImageText = new Texture(Gdx.files.internal("LOGO2.png"));
        Tank1.x = 25;
        Tank1.y = 135;
        Tank1.height = 900;
        Tank1.width = 1000;

        Tank2.x = 430;
        Tank2.y = 16;
        Tank2.height = 900;
        Tank2.width = 1000;

        Tank3.x = 770;
        Tank3.y = 110;
        Tank3.height = 900;
        Tank3.width = 1000;
//        Tank1.
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.SPACE) {
//                    game.setScreen(new GameScreen(game));
//                }
//                return true;
//            }
//        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(100/255f, 100/255f, 200/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
//        font.draw(game.batch, "CHOOSE YOUR TANK ",500,600);
        game.batch.draw(background,0,0);
        game.batch.draw(ImageText,Gdx.graphics.getWidth()/2 - ImageText.getWidth()/2,500);
        game.batch.draw(Tank1Image,Tank1.x,Tank1.y);
        game.batch.draw(Tank2Image,Tank2.x,Tank2.y);
        game.batch.draw(Tank3Image,Tank3.x,Tank3.y);
        game.batch.end();

        stage.act();
        stage.draw();

        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
