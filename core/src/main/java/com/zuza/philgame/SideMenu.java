package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SideMenu extends Window {

    private InventoryWindow inventoryWindow;

    public SideMenu(Skin skin, InventoryWindow inventoryWindow  ) {


        super("Menu", skin);

        this.inventoryWindow = inventoryWindow;

        setSize(200, Gdx.graphics.getHeight());

        setVisible(false);

        TextButton inventoryButton = new TextButton("Inventory", skin);
        add(inventoryButton).pad(5).row();

        inventoryButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventoryWindow.toggle();
                SideMenu.this.setVisible(false);
            }
        });

        TextButton shopButton = new TextButton("Shop", skin);
        add(shopButton).pad(5).row();

        TextButton statsButton = new TextButton("Stats", skin);
        add(statsButton).pad(5).row();

        TextButton settingsButton = new TextButton("Settings", skin);
        add(settingsButton).pad(5).row();
    }



    public void toggle() {
        setVisible(!isVisible());
    }

}
