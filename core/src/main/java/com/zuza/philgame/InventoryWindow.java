package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;


public class InventoryWindow extends Window {

    public InventoryWindow(String title, Skin skin) {
        super(title, skin);
        setSize(300, 300);
        setMovable(true);
        System.out.println("movable is set");
        setResizable(false);

        // The window would not move even with setMovable set to true.
        // Ai suggested the lack of "split" in default uiskin.atlas
        // and additional line of necessary padding to match the split.
        padTop(20);
        row();

        Label titleLabel = getTitleLabel();
        titleLabel.setAlignment(Align.center);

        setBackground(skin.getDrawable("window"));
        setPosition(
            (Gdx.graphics.getWidth() - getWidth()) / 2f,
            (Gdx.graphics.getHeight() - getHeight()) / 2f

        );
        

    }

    public void toggle(){
        setVisible(!isVisible());
    }




}
