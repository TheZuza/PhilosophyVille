package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


public class InventoryWindow extends Window {


   private Slot currentSlot = null;
    public InventoryWindow(String title, Skin skin) {
        super(title, skin);
        setSize(300, 300);
        setMovable(true);
       // System.out.println("movable is set");
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
                final Slot slot = new Slot(skin);
                inventorySquareTable.add(slot);


                if (row == 0 && col == 0) {
                    Assets.tree1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    Drawable treeDrawable = new TextureRegionDrawable(new TextureRegion(Assets.tree1));

                    slot.setItem("tree1", treeDrawable);
                }

                slot.setTouchable(Touchable.enabled);
                slot.addListener(new ClickListener() {
                    @Override public void clicked(InputEvent event, float x, float y) {
                        if (currentSlot != null && currentSlot != slot) {
                            currentSlot.setSelected(false);
                        }
                        if (currentSlot == slot) {

                            slot.setSelected(false);
                            currentSlot = null;
                        } else {
                            slot.setSelected(true);
                            currentSlot = slot;
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


    }

    public void toggle(){
        setVisible(!isVisible());
    }



    public boolean hasSelection() {
        return currentSlot != null && currentSlot.hasItem();
    }


    public void clearSelection() {
        if (currentSlot != null) {
            currentSlot.setSelected(false);
            currentSlot = null;
        }
    }


    public String getSelectedItemId() {
        return hasSelection() ? currentSlot.getItemId() : null;
    }

    public void consumeSelectedItem() {
        if (currentSlot != null) {
            currentSlot.clearItem();
            currentSlot.setSelected(false);
            currentSlot = null;
        }
    }





}
