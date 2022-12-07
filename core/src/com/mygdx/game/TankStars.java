package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.HomeScreen;

import java.awt.*;

public class TankStars extends Game {
	public SpriteBatch batch;

	private OrthographicCamera camera;
	private Texture texture;
	private Texture back;

	@Override
	public void create () {
//		float h = Gdx.graphics.getHeight();
//		float w = Gdx.graphics.getWidth();
//		camera = new OrthographicCamera(500,300*(h/w));
//		camera.position.set(600,300,0);
//		camera.update();
		batch = new SpriteBatch();
//		texture = new Texture(Gdx.files.internal("Tank1.png"));
//		back = new Texture(Gdx.files.internal("image.jpg"));
		setScreen(new HomeScreen(this));
	}

	@Override
	public void render () {
//		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//			camera.zoom += 0.02;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.B)){
//			camera.zoom -= 0.02;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//			camera.translate(-10,0);
//		}
//
//		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//			camera.translate(10,0);
//		}
//
//		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//			camera.translate(0,-10);
//		}
//
//		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//			camera.translate(0,10);
//		}

		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setProjectionMatrix(camera.combined);
//		camera.update();
//		batch.begin();
//		batch.draw(texture,600-texture.getWidth()/2,350-texture.getHeight()/2);
//		batch.draw(back,0,0);
//		batch.end();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
