package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class SideMenu extends Window {

    public SideMenu(Skin skin) {

        //This must be fixed to the middle!
        super("Menu", skin);
        setSize(200, Gdx.graphics.getHeight());
        setPosition(Gdx.graphics.getWidth() - getWidth(), 0);
        setVisible(false);

        TextButton inventoryButton = new TextButton("Inventory", skin);
        add(inventoryButton).pad(5).row();
    }

    public void toggle() {
        setVisible(!isVisible());
    }

}
