package com.zuza.philgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;


public class NPC {

    private final TextureRegion region;
    private int tileX;
    private int tileY;
    private boolean canTalk;

    private boolean  justEntered;
    private boolean  justLeft;
    private long lastSpokeAtMs = -1L;
    private long speakCooldownMs = 1500L;



    public NPC(Texture texture, int tileX, int tileY) {

        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        this.region = new TextureRegion(texture);
        this.tileX = tileX;
        this.tileY = tileY;
    }





    public void update(int playerTileX, int playerTileY) {


        boolean wasNear = this.canTalk;

        int dx = Math.abs(playerTileX - tileX);
        int dy = Math.abs(playerTileY - tileY);

        // Help with AI
        // 3x3 surrounding tiles = Chebyshev distance <= 1, excluding center
        boolean nowNear= (Math.max(dx, dy) <= 1) && !(dx == 0 && dy == 0);



        this.justEntered =  nowNear && !wasNear;
        this.justLeft    = !nowNear &&  wasNear;

        this.canTalk = nowNear;




    }

    public void render(SpriteBatch batch, int tileSize) {
        float x = tileX * tileSize;
        float y = tileY * tileSize;
        batch.draw(region, x, y, tileSize, tileSize);
    }

    public boolean canTalk() { return canTalk; }

    public int getTileX() { return tileX; }
    public int getTileY() { return tileY; }
    public void setTile(int x, int y) { this.tileX = x; this.tileY = y; }


    public void dispose() {

    }


    public boolean justEntered()   { return justEntered; }
    public boolean justLeft()      { return justLeft; }

    //From Ai, for break between dialogue
    public boolean cooldownReady() { return lastSpokeAtMs < 0 || (TimeUtils.millis() - lastSpokeAtMs) >= speakCooldownMs; }
    public void markSpoke()        { lastSpokeAtMs = TimeUtils.millis(); }
   // public void setCooldownMs(long ms) { speakCooldownMs = ms; }



}












