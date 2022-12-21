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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game_Classes.Game_start;
import com.mygdx.game.Game_Classes.Tank;
import com.mygdx.game.TankStars;
import org.w3c.dom.css.Rect;
import  com.badlogic.gdx.scenes.scene2d.ui.Label;

import javax.swing.text.View;
import java.awt.*;
public class ChooseTanks implements Screen{
    private TankStars game;

    private Game_start game_start;

    private Viewport gamePort;

    private Stage stage;

    private BitmapFont font;

    private Texture ImageText;
    private Texture background;
    private Texture Tank1Image;
    private Texture Tank2Image;
    private Texture Tank3Image;

    private TextureRegion textureRegion1;
    private TextureRegion textureRegion2;
    private TextureRegion textureRegion3;
    private TextureRegionDrawable textureRegionDrawable1;
    private TextureRegionDrawable textureRegionDrawable2;
    private TextureRegionDrawable textureRegionDrawable3;

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;

    private OrthographicCamera gameCam;

    public ChooseTanks(TankStars game, Game_start game_start){
        this.game = game;
        this.game_start = game_start;
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
        Tank1Image = new Texture(Gdx.files.internal("ChooseTank_Tank1.png"));
        Tank2Image = new Texture(Gdx.files.internal("ChooseTank_Tank2.png"));
        Tank3Image = new Texture(Gdx.files.internal("ChooseTank_Tank3.png"));
        ImageText = new Texture(Gdx.files.internal("ChooseTank_LOGO.png"));

        textureRegion1 = new TextureRegion(Tank1Image);
        textureRegion2 = new TextureRegion(Tank2Image);
        textureRegion3 = new TextureRegion(Tank3Image);

        textureRegionDrawable1 = new TextureRegionDrawable(textureRegion1);
        textureRegionDrawable2 = new TextureRegionDrawable(textureRegion2);
        textureRegionDrawable3 = new TextureRegionDrawable(textureRegion3);

        imageButton1 = new ImageButton(textureRegionDrawable1);
        imageButton2 = new ImageButton(textureRegionDrawable2);
        imageButton3 = new ImageButton(textureRegionDrawable3);
        imageButton1.setPosition(25,135);
        imageButton2.setPosition(430,16);
        imageButton3.setPosition(770,110);

        stage.addActor(imageButton1);
        stage.addActor(imageButton2);
        stage.addActor(imageButton3);

        imageButton1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game_start.getPlayer1().setTank(new Tank(new GameScreen(game,game_start),100,272,1));
                game_start.getPlayer2().setTank(new Tank(new GameScreen(game,game_start),820,200,2));
                game.setScreen(new GameScreen(game,game_start));
            }
        });
        imageButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game_start.getPlayer1().setTank(new Tank(new GameScreen(game,game_start),100,272,2));
                game_start.getPlayer2().setTank(new Tank(new GameScreen(game,game_start),820,200,2));
                game.setScreen(new GameScreen(game,game_start));
            }
        });
        imageButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game_start.getPlayer1().setTank(new Tank(new GameScreen(game,game_start),100,272,3));
                game_start.getPlayer2().setTank(new Tank(new GameScreen(game,game_start),820,200,2));
                game.setScreen(new GameScreen(game,game_start));
            }
        });

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
        game.batch.draw(background,0,0);
        game.batch.draw(ImageText,Gdx.graphics.getWidth()/2 - ImageText.getWidth()/2,500);
        game.batch.end();

        stage.act();
        stage.draw();

        if(Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game,game_start));
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
