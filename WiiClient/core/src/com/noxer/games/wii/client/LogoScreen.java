package com.noxer.games.wii.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.math.Interpolation.*;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

//Carga el logo de Noxer y lo mueve al final de todo
//setScreen MainMenu
public class LogoScreen implements Screen {

	//private OrthographicCamera camera;
	final Client game;
	private Stage stage;
	private Image image;
	private Texture texture;
	
    public LogoScreen(final Client gam) {
        game = gam;
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);
    }
    
	@Override
	public void show() {
		stage = new Stage();

		texture = new Texture("icon_dos.png");
		image = new Image(texture);
		image.setOrigin(image.getWidth()/2, image.getHeight()/2);
		image.setPosition(Gdx.graphics.getWidth()/2- image.getWidth()/2, Gdx.graphics.getHeight()/2 - image.getHeight()/2);
		image.setScale(0.4f);
		image.addAction(fadeOut(0));
		image.addAction(sequence(parallel(rotateBy(780, 2f, swingOut), fadeIn(1.5f), scaleTo(1.8f, 1.8f, 1.8f)), delay(0.3f),
				parallel(rotateBy(-420, 1f, Interpolation.pow2), scaleTo(0.1f, 0.1f, 1f)), scaleTo(1, 1), delay(0.5f),
				run(new Runnable() {
				    public void run () {
				    	game.setScreen(new MainMenuScreen(game));
			            dispose();
				    }
				})));
		stage.addActor(image);		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//camera.update();
        stage.act(delta);
        stage.draw();
        if(Gdx.input.isTouched()){
        	game.setScreen(new MainMenuScreen(game));
            dispose();
        }
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height);
		//stage.getCamera().update();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		texture.dispose();
	}

}

/*private ScheduledExecutorService programador;
programador = Executors.newSingleThreadScheduledExecutor();
programador.schedule(accion, timepohastalaprimeraejecucion, TimeUnit.MILLISECONDS);
programador.schedule(accion, 2000, TimeUnit.MILLISECONDS);
 private Runnable accion = new Runnable() {
        @Override
        public void run() {}};
        ---------
        *Timer timer = new Timer();
        *TimerTask tas = new TimerTask(){
	        public void run(){
	        }
	    };
	    
	 	timer.schedule(tas, 2900);*/