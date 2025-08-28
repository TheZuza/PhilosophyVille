package com.zuza.philgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grid {

    private Tile[][] tiles;
    private int width, height;
    public static final int TILE_SIZE = 32;


    //constructor
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(Assets.grass1, true);
            }
        }
    }

    public void render(SpriteBatch batch) {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = tiles[x][y];
                batch.draw(tile.getTexture(), x * TILE_SIZE, y * TILE_SIZE);

            }
        }
    }

    public void setTile( int x, int y, Tile tile) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        tiles[x][y] = tile;
    }
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return null;
        return tiles[x][y];
    }

}
