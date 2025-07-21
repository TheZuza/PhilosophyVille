package com.zuza.philgame;

import com.badlogic.gdx.graphics.Texture;

public class Tile {

    Texture texture;
    boolean walkable;

    public Tile(Texture texture, boolean walkable) {
        this.texture = texture;
        this.walkable = walkable;
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
