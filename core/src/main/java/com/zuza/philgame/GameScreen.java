package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private Core game;
    private Grid grid;
    private Sprite sprite;
    private Sprite sprite2;
    private Stage stage;
    private Skin skin;

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

        stage.act(delta);
        stage.draw();
    }
    @Override
    public void show() {

        sprite = new Sprite(10,8);
        stage = new Stage (new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        TextButton menuButton = new TextButton("|||", skin);
        menuButton.setSize(30,20);

        // keeps the button from sticking to the edge
        float padding = 5;
        menuButton.setPosition(
          Gdx.graphics.getWidth() - menuButton.getWidth() - padding,
          Gdx.graphics.getHeight() - menuButton.getHeight() - padding
        );

        menuButton.getStyle().up = null;
        menuButton.getStyle().over = null;
        menuButton.getStyle().down = null;

        SideMenu sideMenu = new SideMenu(skin);
        stage.addActor(sideMenu);


        menuButton.addListener(new ClickListener(){

            public void clicked(InputEvent event, float x, float y) {
                sideMenu.toggle();
            }

        });

        stage.addActor(menuButton);

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
