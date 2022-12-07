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

public class SavedGames implements Screen {

    private TankStars game;
    private Viewport gamePort;
    private OrthographicCamera gameCam;

    private Texture saved;
    private TextButton GAME1;
    private TextButton GAME2;
    private TextButton GAME3;
    private TextButton GAME4;

    private TextButton back;
    private Stage stage;

    public SavedGames(TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/orange/skin/uiskin.json"));

        GAME1 = new TextButton("  GAME 1\n 2 Days Ago",s);
        GAME2= new TextButton("  GAME 2\n 5 Days Ago",s);
        GAME3= new TextButton("  GAME 3\n  5 Hours Ago",s);
        GAME4= new TextButton("  GAME 4\n 1 second Ago",s);

        back = new TextButton("BACK",s);
    }
    @Override
    public void show() {
        stage = new Stage();
        gameCam = new OrthographicCamera();

        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        saved = new Texture("saved.png");

        GAME1.setSize(400, 200);
        GAME1.setPosition(150, 350);

        GAME2.setSize(400, 200);
        GAME2.setPosition(650, 350);

        GAME3.setSize(400, 200);
        GAME3.setPosition(150, 100);

        GAME4.setSize(400, 200);
        GAME4.setPosition(650, 100);

        back.setSize(90,40);
        back.setPosition(18,640);

        stage.addActor(GAME1);
        stage.addActor(GAME2);
        stage.addActor(GAME3);
        stage.addActor(GAME4);
        stage.addActor(back);



        Gdx.input.setInputProcessor(stage);
        GAME1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new GameScreen(game));
            }
        });
        GAME2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new GameScreen(game));
            }
        });
        GAME3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        GAME4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        back.addListener(new ClickListener(){
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
        game.batch.draw(saved,Gdx.graphics.getWidth()/2 - saved.getWidth()/2,600);
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
