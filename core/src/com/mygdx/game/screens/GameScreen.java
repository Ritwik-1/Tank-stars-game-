package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game_Classes.Game_Player;
import com.mygdx.game.Game_Classes.Missile;
import com.mygdx.game.Game_Classes.Tank;
import com.mygdx.game.TankStars;
import org.w3c.dom.Text;

public class GameScreen implements Screen {
    private TankStars game;

    private Stage stage;
    private Texture texture = new Texture(Gdx.files.internal("Bg3.png"));
    private Texture healthbar = new Texture(Gdx.files.internal("healthbar2.png"));

    private Texture play1 = new Texture("Tank2_1.png");
    private Texture play2 = new Texture("Tank2_inverse.png");

    private Game_Player player1;

    private Game_Player player2;
    private TextButton pause;
    private Viewport gamePort;
    private OrthographicCamera gameCam;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Missile missile;

    private Tank tank;

    private Body ground;
//    private PolygonShape groundBox;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

     GameScreen(TankStars game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);
        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        stage = new Stage(gamePort);
        world = new World(new Vector2(0,-100),true);
        maploader = new TmxMapLoader();
        map = maploader.load("untitledmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        debugRenderer = new Box2DDebugRenderer();
    }


    public World getWorld(){
         return world;
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

          // MADE A TANK AND ASSIGNED IT TO A PLAYER
          tank = new Tank(this,100,107,1);

          player1 = new Game_Player(tank);

////          THIS IS A STATIC BODY FOR THE GROUND
//          BodyDef groundBodyDef = new BodyDef();
//          groundBodyDef.position.set(new Vector2(0,50));
//          ground = world.createBody(groundBodyDef);
//          groundBox = new PolygonShape();
//          groundBox.setAsBox(gameCam.viewportWidth,50.0f);
//          ground.createFixture(groundBox,0.0f);

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
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameCam.update();
        renderer.setView(gameCam);
        renderer.render();
        game.batch.setProjectionMatrix(gameCam.combined);
//        game.batch.begin();
//        game.batch.draw(texture,0,0);
//        game.batch.draw(play1,170,186);
//        game.batch.draw(play2,700,204);
//        game.batch.draw(healthbar,90,550);
//        game.batch.end();


        debugRenderer.render(world, gameCam.combined);

//        if(Gdx.input.isTouched()){
//            player1.fireMissile(this);
//        }

//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank.getBody().getPosition().x<1200 && tank.getBody().getPosition().y<700){
//            tank.getBody().applyForceToCenter(10000,0,true);
//        }
//        else if(Gdx.input.isKeyPressed(Input.Keys.D) && pos.x<1200 && pos.y<700 ){
//            tank.getBody().applyForceToCenter(100000000,0,true);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && pos.x>0 && pos.y>0){
//            missile.getBody().applyForceToCenter(-1000,0,true);
//        }
//        else if (Gdx.input.isKeyPressed(Input.Keys.A) && pos.x>0 && pos.y>0){
//            tank.getBody().applyForceToCenter(-100000000,0,true);
//        }
//        else{
//            tank.getBody().setLinearVelocity(new Vector2(0,0));
//            missile.getBody().setLinearVelocity(new Vector2(0,0));
//        }


        world.step(1/60f, 6, 2);


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
//         groundBox.dispose();
    }
}
