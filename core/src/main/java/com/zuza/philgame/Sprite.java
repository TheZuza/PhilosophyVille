package com.zuza.philgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite {

    private Texture texture;
    private float x;
    private float y;

    public Sprite (float startX, float startY){

        this.texture = Assets.sprite;
        this.x = startX;
        this.y = startY;

    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x * Grid.TILE_SIZE, y * Grid.TILE_SIZE, Grid.TILE_SIZE, Grid.TILE_SIZE);
    }


}
