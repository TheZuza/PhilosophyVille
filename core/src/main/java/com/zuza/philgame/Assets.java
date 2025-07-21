package com.zuza.philgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    private static final AssetManager assetManager = new AssetManager();

    public static Texture grass1;
    public static Texture grass2;
    public static Texture sprite;
    public static Texture sprite2;


    public static void load() {
        assetManager.load("grass1.png", Texture.class);
        assetManager.load("grass2.png", Texture.class);
        assetManager.load("sprite.png", Texture.class);
        assetManager.load("sprite2.png", Texture.class);

    }

    public static void finishLoading() {
        assetManager.finishLoading();
        grass1 = assetManager.get("grass1.png", Texture.class);
        grass2 = assetManager.get("grass2.png", Texture.class);
        sprite = assetManager.get("sprite.png", Texture.class);
        sprite2 = assetManager.get("sprite2.png", Texture.class);

    }

    public static void dispose() {
        assetManager.dispose();
    }
}
