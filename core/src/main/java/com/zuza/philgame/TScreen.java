package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



public class TScreen implements Screen {

    private Core game;
    private Grid grid;
    private Sprite sprite;
    private Sprite sprite2;
    private Stage stage;
    private Skin skin;
    private TextButton menuButton;
    private InventoryWindow inventoryWindow;
    private SideMenu  sideMenu;
    private NPC philosopherT;

    public Dialogue dialogue;
    public String philosopherTLesson = " In the philosophy of time travel, there’s a paradox called the bootstrap paradox (or causal loop). It’s when an object or piece of information is sent back in time and becomes the very cause of itself existing in the future.";
    private boolean saidIt =  false;

    public TScreen(Core game) {
        this.grid = new Grid(20, 15);
        this.game = game;

    }

    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1); // clears grid before drawing with solid colour

        sprite.update(delta);

        game.batch.begin();
        grid.render(game.batch);
        sprite.render(game.batch);

        philosopherT.render(game.batch, Grid.TILE_SIZE);

        int playerTileX = (int) sprite.getX();
        int playerTileY = (int) sprite.getY();

        philosopherT.update(playerTileX, playerTileY);


        if (philosopherT.justEntered() && philosopherT.cooldownReady()) {
            dialogue.show(philosopherTLesson);
            philosopherT.markSpoke();
        }

        if (philosopherT.justLeft() && dialogue.isShowing()) {
            dialogue.hide();
        }



        game.batch.end();

        stage.act(delta);
        stage.draw();


    }
    @Override

    public void show() {

        sprite = new Sprite(9, 14);

        philosopherT = new NPC (Assets.tsprite, 10, 7);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"),
            new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"))
        );

        dialogue = new Dialogue(skin);
        stage.addActor(dialogue);


        Actor worldInput = new Actor();
        worldInput.setBounds(0, 0, 20 * Grid.TILE_SIZE, 15 * Grid.TILE_SIZE);
        worldInput.setTouchable(Touchable.enabled);
        worldInput.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Use stage coords to compute tile
                float sx = event.getStageX();
                float sy = event.getStageY();
                int tileX = (int)(sx / Grid.TILE_SIZE);
                int tileY = (int)(sy / Grid.TILE_SIZE);
                sprite.moveTo(tileX, tileY);
                return true;
            }
        });
        stage.addActor(worldInput);


        menuButton = new TextButton("|||", skin);
        menuButton.setSize(30, 20);

        // remove button visuals
        menuButton.getStyle().up = null;
        menuButton.getStyle().over = null;
        menuButton.getStyle().down = null;

        inventoryWindow = new InventoryWindow("Inventory", skin);
        sideMenu = new SideMenu(skin, inventoryWindow);

        // Ensure UI is touchable
        menuButton.setTouchable(Touchable.enabled);
        sideMenu.setTouchable(Touchable.enabled);
        inventoryWindow.setTouchable(Touchable.enabled);
        inventoryWindow.setModal(true);         // prevent clicks passing through when open, so sprite an such wont move on board
        inventoryWindow.setVisible(false);

        stage.addActor(sideMenu);
        stage.addActor(inventoryWindow);
        inventoryWindow.toFront();

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sideMenu.toggle();
            }
        });

        // Click outside inventory window to close it
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (inventoryWindow.isVisible()) {
                    float sx = event.getStageX();
                    float sy = event.getStageY();
                    Actor hit = stage.hit(sx, sy, true);
                    boolean clickedInsideInventory =
                        (hit != null) && hit.isDescendantOf(inventoryWindow);

                    if (!clickedInsideInventory) {
                        inventoryWindow.setVisible(false);
                        return true; // consume so it doesn't touch the board
                    }
                }
                return false;
            }
        });

        stage.addActor(menuButton);

        // Layout
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



        menuButton.setTouchable(Touchable.enabled);
        sideMenu.setTouchable(Touchable.enabled);
        inventoryWindow.setTouchable(Touchable.enabled);



        stage.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!sideMenu.isVisible()) return false; // nothing to do

                float sx = event.getStageX();
                float sy = event.getStageY();
                Actor hit = stage.hit(sx, sy, true); // true => respect Touchable

                boolean onSideMenu   = hit != null && hit.isDescendantOf(sideMenu);
                boolean onMenuButton = hit != null && (hit == menuButton || hit.isDescendantOf(menuButton));

                // If click is not on the side menu  close it
                if (!onSideMenu && !onMenuButton) {
                    sideMenu.setVisible(false);
                    return true; //  won't move the sprite
                }
                return false;
            }
        });









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
