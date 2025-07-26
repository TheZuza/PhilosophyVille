package com.zuza.philgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;


public class Sprite {

    private final Map<SpriteState, Animation<TextureRegion>> animations = new HashMap<>();
    private SpriteState currentState;
    private float stateTime; // req for animation frames
    private float x;
    private float y;

    public Sprite(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.stateTime = 0f;

        loadAnimations();
        currentState = SpriteState.RESTING;
    }


    private void loadAnimations() {

        Texture restingSheet = new Texture("Sprite3-sheet.png");
        animations.put(SpriteState.RESTING, createAnimation(restingSheet, 4, 1, 0.2f));

    }

    // Some help from Ai to change from normal graphics to animation derived from a frame sheet.
    private Animation<TextureRegion> createAnimation (Texture sheet, int cols, int rows, float frameDuration) {
        TextureRegion[][] textureRegions = TextureRegion.split(sheet, sheet.getWidth()/ cols, sheet.getHeight()/ rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = textureRegions[i][j];
            }
        }
        return new Animation<>(frameDuration, frames);
    }

    public void setState(SpriteState newState) {
        if(newState != currentState) {
            currentState = newState;
            stateTime = 0f; // reset animation timer after changing animations
        }
    }

    public void render(SpriteBatch batch) {
        Animation<TextureRegion> animation = animations.get(currentState);
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
       // batch.draw(currentFrame, x, y);
        batch.draw(currentFrame, x * Grid.TILE_SIZE, y * Grid.TILE_SIZE, Grid.TILE_SIZE, Grid.TILE_SIZE);
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void dispose() {
        for (Animation<TextureRegion> animation : animations.values()) {

        }
    }


}
