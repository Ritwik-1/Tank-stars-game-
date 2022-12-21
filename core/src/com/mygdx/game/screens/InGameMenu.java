package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
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
import com.mygdx.game.Game_Classes.Game_start;
import com.mygdx.game.TankStars;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class InGameMenu implements Screen {
    private TankStars game;
    private Viewport gamePort;
    private OrthographicCamera gameCam;

    private Game_start Ongame;

    private Stage stage;
    private Texture PauseMenu;
    private TextButton RESUME_GAME;
    private TextButton SAVE_GAME;
    private TextButton MAIN_MENU;
    private TextButton EXIT_GAME;



    public InGameMenu(TankStars game, Game_start game_start){
        this.game = game;
        this.Ongame = game_start;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/orange/skin/uiskin.json"));

        RESUME_GAME = new TextButton("RESUME GAME",s);
        SAVE_GAME = new TextButton("SAVE GAME",s);
        MAIN_MENU = new TextButton("MAIN MENU",s);
        EXIT_GAME = new TextButton("EXIT GAME",s);
    }

    public void serialize() throws IOException{
        Game_start g1 = Ongame;
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(g1);
        }
        finally {
            out.close();
        }
    }


    @Override
    public void show() {
        stage = new Stage();
        gameCam = new OrthographicCamera();

        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        PauseMenu = new Texture(Gdx.files.internal("PauseMenu_Heading.png"));

        RESUME_GAME.setSize(300, 100);
        RESUME_GAME.setPosition(Gdx.graphics.getWidth()/2 -150, 490);

        SAVE_GAME.setSize(300, 100);
        SAVE_GAME.setPosition(Gdx.graphics.getWidth()/2 -150, 340);

        MAIN_MENU.setSize(300, 100);
        MAIN_MENU.setPosition(Gdx.graphics.getWidth()/2 -150, 190);

        EXIT_GAME.setSize(300, 100);
        EXIT_GAME.setPosition(Gdx.graphics.getWidth()/2 -150, 40);

        stage.addActor(RESUME_GAME);
        stage.addActor(SAVE_GAME);
        stage.addActor(MAIN_MENU);
        stage.addActor(EXIT_GAME);

        Gdx.input.setInputProcessor(stage);
        RESUME_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new GameScreen(game,Ongame));
            }
        });
        SAVE_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    serialize();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                game.setScreen(new SavedGames(game,Ongame));
            }
        });
        MAIN_MENU.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
            }
        });
        EXIT_GAME.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
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
