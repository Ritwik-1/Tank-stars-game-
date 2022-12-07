package com.mygdx.game.screens;

import com.badlogic.gdx.*;
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
import org.w3c.dom.Text;

public class GameScreen implements Screen {
    private TankStars game;

    private Stage stage;
    private Texture texture = new Texture(Gdx.files.internal("Bg3.png"));
    private Texture healthbar = new Texture(Gdx.files.internal("healthbar2.png"));

    private Texture play1 = new Texture("Tank2_1.png");
    private Texture play2 = new Texture("Tank2_inverse.png");
    private TextButton pause;
    private Viewport gamePort;
    private OrthographicCamera gameCam;

     GameScreen(TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);

        stage = new Stage(gamePort);
    }

    @Override
    public void show() {
        gameCam.setToOrtho(false,1200,700);
        game.batch = new SpriteBatch();

        Skin s = new Skin(Gdx.files.internal("gdx-skins-master/orange/skin/uiskin.json"));

        pause = new TextButton("PAUSE",s);

        pause.setSize(90,40);
        pause.setPosition(18,640);

        stage.addActor(pause);

        Gdx.input.setInputProcessor(stage);
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new InGameMenu(game));
            }
        });
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.SPACE) {
//                    game.setScreen(new InGameMenu(game));
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
        game.batch.draw(texture,0,0);
        game.batch.draw(play1,170,186);
        game.batch.draw(play2,700,204);
        game.batch.draw(healthbar,90,550);
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
