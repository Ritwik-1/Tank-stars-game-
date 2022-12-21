package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
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
import com.mygdx.game.Game_Classes.Game_start;
import com.mygdx.game.Game_Classes.Missile;
import com.mygdx.game.Game_Classes.Tank;
import com.mygdx.game.TankStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameScreen implements Screen {

    // **** ALL libgdx things **** //
    private TankStars game;

    private Stage stage;
    private Viewport gamePort;
    private OrthographicCamera gameCam;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private TextButton pause;
    private TextButton FIRE1;
    private TextButton FIRE2;

    // **** All libgdx things  **** //

    // GAME
    private Game_start game_start;
    private Game_Player player1;

    private Game_Player player2;

    private Tank tank1;
    private Tank tank2;
    private Missile missile;

    private Body ground;


    // FLAGS AND ETC
    private int Turn = 1;

    private int flagForMissile1 = 0;
    private int flagForMissile2 = 0;


    private float Player1_vx = 400;
    private float Player1_vy = 500;

    private float Player2_vx = -600;
    private float Player2_vy = 700;

    private float time = 0;


    private Sprite tank2Sprite;


    private Array<Body> tempBodies = new Array<Body>();

    private GameScreen gg = this;

     GameScreen(TankStars game, Game_start game_start){
         this.game_start = game_start;
        this.player1 = game_start.getPlayer1();
        this.player2 = game_start.getPlayer2();
        this.tank1 = game_start.getPlayer1().getPlayer_tank();
        this.tank2 = game_start.getPlayer2().getPlayer_tank();

        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(1200,700,gameCam);
        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        stage = new Stage(gamePort);

        maploader = new TmxMapLoader();
        map = maploader.load("untitledmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0,-50),true);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth()/2, rect.getY()+ rect.getHeight()/2);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight()/ 2);
            fdef.shape = shape;
            fdef.density = 2f;
            fdef.friction = 0.9f;
            fdef.restitution = 0;
            body.createFixture(fdef);
        }
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

        FIRE1 = new TextButton("FIRE!",s);
        FIRE2 = new TextButton("FIRE!",s);

        FIRE1.setSize(80,40);
        FIRE2.setSize(80,40);
        FIRE1.setPosition(50,170);
        FIRE2.setPosition(1050,170);

        FIRE1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(flagForMissile1 == 0){
//                    System.out.println("I am in FIRE1");
                    missile = new Missile(gg, tank1.getBody().getPosition().x+5, tank1.getBody().getPosition().y+5);
                    player1.fireMissile(missile,Player1_vx,Player1_vy);
                    System.out.println("Missile fired!!");
                    flagForMissile1 = 1;
//                    System.out.println("I am after flagForMissile1");
//                    System.out.println("flag1 : "+flagForMissile1);
                }

            }
        });

        FIRE2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(flagForMissile2 == 0){
                    flagForMissile2 = 1;
                    Missile missile = new Missile(gg,tank2.getBody().getPosition().x+5,tank2.getBody().getPosition().y+5);
                    player2.fireMissile(missile,Player2_vx,Player2_vy);
                }
            }
        });

        pause.setSize(90,40);
        pause.setPosition(18,640);

        stage.addActor(pause);


        Gdx.input.setInputProcessor(stage);
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new InGameMenu(game,game_start));
            }
        });

          // MADE A TANK AND ASSIGNED IT TO A PLAYER

//          game_start.getPlayer1().getPlayer_tank().setDensity(5f);
//          game_start.getPlayer1().getPlayer_tank().setFriction(2f);
//          game_start.getPlayer1().getPlayer_tank().setRestitution(0);
//          game_start.getPlayer1().getPlayer_tank().setPosition_Tank(100,272);
          tank1 = new Tank(this,100,272,1);
          tank1.setDensity(5f);
          tank1.setFriction(2f);
          tank1.setRestitution(0);



          player1 = new Game_Player();
          player1.setTank(tank1);

//            game_start.getPlayer2().getPlayer_tank().setDensity(5f);
//            game_start.getPlayer2().getPlayer_tank().setFriction(2f);
//            game_start.getPlayer2().getPlayer_tank().setRestitution(0);
//            game_start.getPlayer2().getPlayer_tank().setPosition_Tank(820,200);
          tank2 = new Tank(this,820,200,2);
          tank2.setDensity(5f);
          tank2.setFriction(2f);
          tank2.setRestitution(0);

          player2 = new Game_Player();
          player2.setTank(tank2);

            stage.addActor(FIRE1);
            stage.addActor(FIRE2);

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

    public void Play_Game(float delta){
        if(Turn == 1){
//                player2.getPlayer_tank().getBodyDef().type = BodyDef.BodyType.StaticBody;
            player2.getPlayer_tank().getBody().setLinearVelocity(new Vector2(0,0));
            System.out.println("This is in render flag1 :"+flagForMissile1);
            if(flagForMissile1 == 1){
                System.out.println("Entered here in flagforM1");
                float TimeOfFlight = (2*(Player1_vy))/(50);
                System.out.println("Time of Flight : "+TimeOfFlight);
//                while(time < (TimeOfFlight)){
//                    time = time + delta;
//                    System.out.println("Time : "+time);
//                }
                time = time + delta;

                System.out.println("Destroying missile");
                world.destroyBody(missile.getBody());
                System.out.println("Destroyed missile");
                Turn = 2;
                time = 0;
                flagForMissile1 = 0;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.getBody().getPosition().x<1200 && tank1.getBody().getPosition().y<700){
                player1.getPlayer_tank().getBody().applyForceToCenter(new Vector2(12000,0),true);

            }
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.getBody().getPosition().x>0 && tank1.getBody().getPosition().y>0) {
                player1.getPlayer_tank().getBody().applyForceToCenter(new Vector2(-12000, 0), true);
            }

        }
        else {
            player1.getPlayer_tank().getBody().setLinearVelocity(new Vector2(0,0));
//                player1.getPlayer_tank().getBodyDef().type = BodyDef.BodyType.StaticBody;
            if(flagForMissile2 == 1){
                float TimeOfFlight = (2*(Player2_vy))/(-1*world.getGravity().y);
                while((time + delta) < TimeOfFlight){
                }
                world.destroyBody(missile.getBody());
                time = 0;
                Turn = 1;
                flagForMissile2 = 0;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.getBody().getPosition().x<1200 && tank1.getBody().getPosition().y<700){
                player2.getPlayer_tank().getBody().applyForceToCenter(new Vector2(12000,0),true);

            }
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.getBody().getPosition().x>0 && tank1.getBody().getPosition().y>0){
                player2.getPlayer_tank().getBody().applyForceToCenter(new Vector2(-12000,0),true);
            }

        }
    }

    public int EndGame(){
         return 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCam.update();
        renderer.setView(gameCam);
        renderer.render();
        game.batch.setProjectionMatrix(gameCam.combined);
        debugRenderer.render(world, gameCam.combined);

        game.batch.begin();
        world.getBodies(tempBodies);
        for(Body b : tempBodies){
            if(b.getUserData() != null && b.getUserData() instanceof  Sprite){
                Sprite sprite = (Sprite)b.getUserData();
                sprite.setPosition(b.getPosition().x-sprite.getWidth()/2,b.getPosition().y -sprite.getHeight()/2);
                sprite.draw(game.batch);
            }
        }
        game.batch.end();

        Play_Game(delta);

        world.step(1/60f, 6, 2);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/60f));
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
