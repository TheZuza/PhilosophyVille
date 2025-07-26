package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private TextButton menuButton;
    private InventoryWindow inventoryWindow;
    private SideMenu  sideMenu;

    public GameScreen(Core game) {
        this.grid = new Grid(20, 15);
        this.game = game;

    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1); // clears grid before drawing with solid colour

        sprite.update(delta);

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

        menuButton = new TextButton("|||", skin);
        menuButton.setSize(30,20);


        // removes the padding under the button

        menuButton.getStyle().up = null;
        menuButton.getStyle().over = null;
        menuButton.getStyle().down = null;


        inventoryWindow = new InventoryWindow("Inventory", skin);
        sideMenu = new SideMenu(skin, inventoryWindow );
        stage.addActor(sideMenu);
        stage.addActor(inventoryWindow);
        inventoryWindow.toFront();
        inventoryWindow.setTouchable(Touchable.enabled);
        //inventoryWindow.debug();

        inventoryWindow.setVisible(false);


        menuButton.addListener(new ClickListener(){

            public void clicked(InputEvent event, float x, float y) {
                sideMenu.toggle();
            }

        });

        stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (inventoryWindow.isVisible()) {
                    if (inventoryWindow.hit(x-inventoryWindow.getX(), y - inventoryWindow.getY(), true) == null){
                        inventoryWindow.setVisible(false);
                    }
                }
                return false;
            }

        });

        stage.addActor(menuButton);

        // Help from : https://libgdx.com/wiki/graphics/2d/scene2d/table
        // Note to self: This should be moved to its own class?
        Table menuButtonTable = new Table();
        menuButtonTable.setFillParent(true);
        stage.addActor(menuButtonTable);

        menuButtonTable.top().right().pad(10);
        menuButtonTable.add(menuButton).width(menuButton.getWidth()).height(menuButton.getHeight());

        Table sideMenuTable = new Table();
        sideMenuTable.setFillParent(true);
        stage.addActor(sideMenuTable);
        sideMenuTable.top().right().pad(10);
        sideMenuTable.add(sideMenu).width(sideMenu.getWidth()).height(sideMenu.getHeight());

    }



    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);    //makes sure everything works after resize

    }
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
