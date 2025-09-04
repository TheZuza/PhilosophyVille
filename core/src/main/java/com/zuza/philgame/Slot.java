package com.zuza.philgame;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;


public class Slot extends Stack {
    private final Skin skin;
    private final Image bg;
    private final Image icon;
    private String itemId;

    public Slot(Skin skin) {
        this.skin = skin;
        setSize(32, 32);

        bg = new Image(skin.getDrawable("spinner-textfield"));
        icon = new Image();

        // Help from AI
        icon.setScaling(Scaling.fit);
        icon.setAlign(Align.center);

        add(bg);
        add(icon);
    }

    public void setSelected(boolean selected) {

        //Help from AI
        bg.setDrawable(skin.getDrawable(
            selected ? "spinner-textfield-selected" : "spinner-textfield"
        ));
    }

    public void setItem(String id,Drawable d) {

        this.itemId = id;
        icon.setDrawable(d);
        icon.setVisible(d != null);

    }

    public String getItemId() { return itemId; }
    public boolean hasItem(){return icon.getDrawable() != null;}

    public void setItemDrawable(Drawable d) {
        icon.setDrawable(d);
        icon.setVisible(d != null);
    }

    public void clearItem() {
        setItemDrawable(null);
    }


}

