package com.zuza.philgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
    private final Core game;
    private final Grid grid;
    private Sprite sprite;

    private Stage stage;
    private Skin skin;
    private TextButton menuButton;
    private InventoryWindow inventoryWindow;
    private SideMenu sideMenu;

    private static final int GRID_W = 20;
    private static final int GRID_H = 15;

    private static final int PORTAL_X = 19;
    private static final int PORTAL_Y = 7;
    private boolean transitioning = false;



    //This small class used AI suggestion
    private static class Placed {
        final int x, y;
        final Texture tex;
        Placed(int x, int y, Texture tex) { this.x = x; this.y = y; this.tex = tex; }
    }
    private final Array<Placed> placed = new Array<>();



    public GameScreen(Core game) {
        this.game = game;
        this.grid = new Grid(GRID_W, GRID_H);
    }

    @Override
    public void show() {
        sprite = new Sprite(10, 8);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Debug method to see what the stage receives. Suggested by the AI
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor hit = stage.hit(event.getStageX(), event.getStageY(), true);
                String who = (hit == null) ? "null" : hit.getClass().getSimpleName();
                Gdx.app.log("STAGE", "down @" + (int)event.getStageX() + "," + (int)event.getStageY() + " hit=" + who);
                return false;
            }
        });

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));


        Hud hud = new Hud(skin, game.state());
        stage.addActor(hud);


        grid.setTile(19, 7, new Tile(Assets.gate, true));


        Actor worldInput = new Actor();
        worldInput.setTouchable(Touchable.enabled);
        worldInput.setBounds(
            0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight()
        );
        worldInput.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int tileX = (int)(event.getStageX() / Grid.TILE_SIZE);
                int tileY = (int)(event.getStageY() / Grid.TILE_SIZE);


                if (tileX < 0 || tileX >= GRID_W || tileY < 0 || tileY >= GRID_H) {
                    return true;
                }


                boolean hasSel = inventoryWindow.hasSelection();
                System.out.println("[WORLD] tile " + tileX + "," + tileY + " hasSelection=" + hasSel);

                if (hasSel) {

                    if (game.state().getPoints() >= 50){

                    if(!isAlreadyPlanted(tileX, tileY)){
                    placed.add(new Placed(tileX, tileY, Assets.tree1));
                   // System.out.println("[WORLD] PLANTED tree at " + tileX + "," + tileY);
                    game.state().addPoints(-50);
                    } } else {
                            System.out.println(" not enough points " + tileX + "," + tileY);
                    }
                    inventoryWindow.clearSelection();
                    return true;
                }


                sprite.moveTo(tileX, tileY);
                return true;
            }
        });
        stage.addActor(worldInput);


        menuButton = new TextButton("|||", skin);
        menuButton.setSize(30, 20);
        menuButton.getStyle().up = null;
        menuButton.getStyle().over = null;
        menuButton.getStyle().down = null;

        inventoryWindow = new InventoryWindow("Inventory", skin);
        sideMenu = new SideMenu(skin, inventoryWindow);

        inventoryWindow.setTouchable(Touchable.enabled);
        inventoryWindow.setModal(false);
        inventoryWindow.setVisible(false);

        sideMenu.setTouchable(Touchable.enabled);
        menuButton.setTouchable(Touchable.enabled);

        stage.addActor(sideMenu);
        stage.addActor(inventoryWindow);
        stage.addActor(menuButton);
        inventoryWindow.toFront();

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sideMenu.toggle();
            }
        });

        // Click outside inventory to close it, but DO NOT consume the event
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (inventoryWindow.isVisible()) {
                    Actor hit = stage.hit(event.getStageX(), event.getStageY(), true);
                    boolean inside = hit != null && hit.isDescendantOf(inventoryWindow);
                    if (!inside) {
                        inventoryWindow.setVisible(false);
                        return false;
                    }
                }
                return false;
            }
        });

        // Close ONLY the side menu when clicking on empty stage space (do not consume)
        stage.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!sideMenu.isVisible()) return false;

                Actor hit = stage.hit(event.getStageX(), event.getStageY(), true);
                boolean onSideMenu   = hit != null && hit.isDescendantOf(sideMenu);
                boolean onMenuButton = hit != null && (hit == menuButton || hit.isDescendantOf(menuButton));

                if (!onSideMenu && !onMenuButton) {
                    sideMenu.setVisible(false);
                    return false; // allow world click through
                }
                return false;
            }
        });


        Table menuButtonTable = new Table();
        menuButtonTable.setFillParent(true);
        menuButtonTable.setTouchable(Touchable.childrenOnly);
        stage.addActor(menuButtonTable);
        menuButtonTable.top().right().pad(10);
        menuButtonTable.add(menuButton).width(menuButton.getWidth()).height(menuButton.getHeight());

        Table sideMenuTable = new Table();
        sideMenuTable.setFillParent(true);
        sideMenuTable.setTouchable(Touchable.childrenOnly);
        stage.addActor(sideMenuTable);
        sideMenuTable.top().right().pad(10);
        sideMenuTable.add(sideMenu).width(sideMenu.getWidth()).height(sideMenu.getHeight());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        sprite.update(delta);

        if (!transitioning && isSpriteOnPortal()) {
            transitioning = true;
            game.setScreen(new NScreen(game));
        }

        game.batch.begin();
        grid.render(game.batch);

        for (Placed p : placed) {
            game.batch.draw(
              p.tex,
              p.x * Grid.TILE_SIZE,
              p.y * Grid.TILE_SIZE,
              Grid.TILE_SIZE, Grid.TILE_SIZE
            );
        }

        sprite.render(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    private boolean isSpriteOnPortal() {
        float sx = sprite.getX();
        float sy = sprite.getY();
        return sx == PORTAL_X && sy == PORTAL_Y;
    }

    // helper method suggested by the AI
    private boolean isAlreadyPlanted(int x, int y) {
        for (Placed p : placed) if (p.x == x && p.y == y) return true;
        return false;
    }


}
