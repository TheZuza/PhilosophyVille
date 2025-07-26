package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;


public class InventoryWindow extends Window {

    private Image currentSlot = null; // will keep track of witch slot in the inventory window was previously selected

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

        Table inventorySquareTable = new Table();
        inventorySquareTable.defaults().size(32, 32).pad(4);
        for ( int row = 0; row < 4; row ++){
            for ( int col = 0; col < 7; col ++){
                Image slot =  new Image(skin.getDrawable("spinner-textfield"));//spinner-textfield-selected
                inventorySquareTable.add(slot);

                slot.setTouchable(Touchable.enabled);

                slot.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                       if (currentSlot != null && currentSlot != slot){
                           currentSlot.setDrawable(skin.getDrawable("spinner-textfield"));
                       }


                        Drawable current = slot.getDrawable();
                        if (current == skin.getDrawable("spinner-textfield")) {
                            slot.setDrawable(skin.getDrawable("spinner-textfield-selected"));
                            currentSlot = slot;
                        } else{
                            slot.setDrawable(skin.getDrawable("spinner-textfield"));
                            currentSlot = null;
                        }
                    }
                });
            }

            //inventorySquareTable.debug();
            inventorySquareTable.row();

        }
        Table wrapper = new Table();
        wrapper.add(inventorySquareTable).left().top();
        add(wrapper).expand().top().left().pad(10);
       // add(inventorySquareTable).left().top().padLeft(10).padTop(10);

    }

    public void toggle(){
        setVisible(!isVisible());
    }




}
