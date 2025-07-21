package com.zuza.philgame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private  Core game;
    private Grid grid;
    private Sprite sprite;
    private Sprite sprite2;

    public GameScreen(Core game) {
        this.grid = new Grid(20, 15);
        this.game = game;

    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1); // clears grid before drawing with solid colour
        game.batch.begin();
        grid.render(game.batch);
        sprite.render(game.batch);
        game.batch.end();
    }
    @Override
    public void show() {

        sprite = new Sprite(10,8);
    }
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {

    }





}
