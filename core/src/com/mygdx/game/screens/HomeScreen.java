package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

import javax.swing.text.View;
import java.awt.*;


public class HomeScreen implements Screen {
    private TankStars game;
    private Viewport gamePort;
    private OrthographicCamera gameCam;
    private Stage stage;
    private TextButton settings;
    private TextButton START_GAME;
    private TextButton RESUME_GAME;
    private TextButton EXIT_GAME;
    private Texture backgroundImage;

    public HomeScreen(final TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/orange/skin/uiskin.json"));

        START_GAME = new TextButton("START GAME", s);
        RESUME_GAME = new TextButton("RESUME GAME",s);
        EXIT_GAME = new TextButton("EXIT GAME",s);
        settings = new TextButton("SETTINGS",s);


        stage = new Stage(gamePort);
//        l1 = new Label("cnd", s);

    }
    @Override
    public void show() {


        START_GAME.setSize(300, 100);
        START_GAME.setPosition(820, 470);

        RESUME_GAME.setSize(300, 100);
        RESUME_GAME.setPosition(820, 320);

        EXIT_GAME.setSize(300, 100);
        EXIT_GAME.setPosition(820, 170);

        settings.setSize(90,40);
        settings.setPosition(18,640);

        stage.addActor(START_GAME);
        stage.addActor(RESUME_GAME);
        stage.addActor(EXIT_GAME);
        stage.addActor(settings);

        backgroundImage = new Texture(Gdx.files.internal("Tank_Stars_home.jpg"));


        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);
        START_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new ChooseTanks(game));
            }
        });
        RESUME_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new SavedGames(game));
            }
        });
        EXIT_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        settings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsMain(game));
            }
        });

//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.SPACE) {
//                    game.setScreen(new ChooseTanks(game));
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);



        game.batch.begin();
        game.batch.draw(backgroundImage,0,0);
        game.batch.end();

        stage.act();
        stage.draw();
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
