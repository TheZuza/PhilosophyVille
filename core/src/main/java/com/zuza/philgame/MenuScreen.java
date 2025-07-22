package com.zuza.philgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

// Not sure if this class is needed. A main menu class that would exit the game state.


public class MenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private Core game;

    public MenuScreen(Core game){
        this.game = game;
    }




    // Main game loop, draw screen, updates game logic
    // Delta --> times in second since the last render call
    @Override
    public void render(float delta) {

    }

    // Adjust a viewpoint
    @Override
    public void resize(int width, int height) {

    }

    // Initialise resources, called once when screen becomes current screen
    @Override
    public void show() {

    }

    // Called when the screen is no longer the Current Screen.
    // Free resources that are not needed when screen is inactive
    @Override
    public void hide() {


    }

    // Useful for mobile apps. Pause background resources such as music
    @Override
    public void pause() {

    }

    // Resume resources once game is back into focus
    @Override
    public void resume() {

    }

    // Free up memory by destroying no longer needed resources
    @Override
    public void dispose() {

    }
}
