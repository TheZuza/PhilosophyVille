package com.zuza.philgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    protected SpriteBatch batch;
    private Texture image;
    private final GameState state =  new GameState();

    @Override
    public void create() {
        batch = new SpriteBatch();
        Assets.load();
        Assets.finishLoading();
        setScreen(new GameScreen(this));
    }

    public GameState state() {return state;}



    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();
    }
}
