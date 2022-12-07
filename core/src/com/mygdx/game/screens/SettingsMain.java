package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

public class SettingsMain implements Screen {
    private TankStars game;
    private Viewport gamePort;
    private OrthographicCamera gameCam;

    private Stage stage;
    private Texture PauseMenu;
    private TextButton SOUND;
    private TextButton MUSIC;
    private TextButton MAIN_MENU;
    private TextButton TERMS;
    public SettingsMain(TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/orange/skin/uiskin.json"));

        SOUND = new TextButton("SOUND",s);
        MUSIC = new TextButton("MUSIC",s);
        MAIN_MENU= new TextButton("MAIN MENU",s);
        TERMS= new TextButton("TERMS AND PRIVACY",s);
    }
    @Override
    public void show() {
        stage = new Stage();
        gameCam = new OrthographicCamera();

        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        PauseMenu = new Texture(Gdx.files.internal("Settings.png"));

        SOUND.setSize(300, 100);
        SOUND.setPosition(Gdx.graphics.getWidth()/2 -150, 490);

        MUSIC.setSize(300, 100);
        MUSIC.setPosition(Gdx.graphics.getWidth()/2 -150, 340);

        MAIN_MENU.setSize(300, 100);
        MAIN_MENU.setPosition(Gdx.graphics.getWidth()/2 -150, 190);

        TERMS.setSize(300, 100);
        TERMS.setPosition(Gdx.graphics.getWidth()/2 -150, 40);

        stage.addActor(SOUND);
        stage.addActor(MUSIC);
        stage.addActor(MAIN_MENU);
        stage.addActor(TERMS);

        Gdx.input.setInputProcessor(stage);
        SOUND.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new HomeScreen(game));
            }
        });
        MUSIC.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new HomeScreen(game));
            }
        });
        MAIN_MENU.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
            }
        });
        TERMS.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(48/255f,26/255f,71/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(PauseMenu,Gdx.graphics.getWidth()/2 - PauseMenu.getWidth()/2,620);
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
