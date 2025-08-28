package com.zuza.philgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Sprite {

    private final Map<SpriteState, Animation<TextureRegion>> animations = new HashMap<>();
    private SpriteState currentState;
    private float stateTime; // req for animation frames
    private float x;
    private float y;


   // private float targetX; // target coordinates for movemkent
   // private float targetY;
    private float speed= 3f;  // move with 5 tiles per second

    private Queue<Vector2> path = new LinkedList<>();
    private Vector2 currentTarget = null;

    public Sprite(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        //this.stateTime = 0f;

       // this.targetX = startX;
       // this.targetY = startY;

        loadAnimations();
        currentState = SpriteState.RESTING;
    }

    public void moveTo(float tileX, float tileY) {

        path.clear(); // clear the cue first

        // tile movement
        int startX = (int) x;
        int startY = (int) y;
        int endX = (int) tileX;
        int endY = (int) tileY;

        // Movement horizontally

        int ax = (endX > startX) ? 1 : -1;
        while (startX != endX){
         startX += ax;
         path.add(new Vector2(startX, startY));



        }



        int ay = (endY > startY) ? 1 : -1;
        while (startY != endY){
            startY += ay;
            path.add(new Vector2(endX, startY));
        }






        if (!path.isEmpty()){
            currentTarget= path.poll();
        }





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
        stateTime += delta; // for animation

       if (currentTarget == null) {
           return;
       }


        float dx = currentTarget.x - x;
        float dy = currentTarget.y - y;

        float distance =  (float) Math.sqrt(dx * dx + dy * dy);
        float moveAmount = delta * speed;

        if (distance <= moveAmount) {
            x = currentTarget.x;
            y = currentTarget.y;

            if (!path.isEmpty()) {
                currentTarget = path.poll();
            } else {
                currentTarget = null;
            }
        } else {
            x += (dx / distance) * moveAmount;
            y += (dy / distance) * moveAmount;







        }







    }

    public void dispose() {
        for (Animation<TextureRegion> animation : animations.values()) {

        }
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


}
