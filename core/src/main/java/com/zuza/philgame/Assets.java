package com.zuza.philgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private static final AssetManager assetManager = new AssetManager();

    public static Texture grass1;
    public static Texture grass2;
    public static Texture sprite;
    public static Texture sprite2;
    public static Texture gate;
    public static Texture nsprite;
    public static Texture tsprite;

    private static final String UI_ATLAS_PATH = "ui/uiskin.atlas";
    private static final String UI_SKIN_PATH  = "ui/uiskin.json";
   // private static Skin skin;
   // private static TextureAtlas uiAtlas;



    public static void load() {
        assetManager.load("grass1.png", Texture.class);
        assetManager.load("grass2.png", Texture.class);
        assetManager.load("sprite.png", Texture.class);
        assetManager.load("sprite2.png", Texture.class);
        assetManager.load("gate.png", Texture.class);
        assetManager.load("nnpc1.png", Texture.class);
        assetManager.load("TimeSprite.png", Texture.class);


        // AI suggestion to fix dialogue box, but it seems not to have an effect
        assetManager.load(UI_ATLAS_PATH, TextureAtlas.class);
        assetManager.load(UI_SKIN_PATH, Skin.class, new SkinLoader.SkinParameter(UI_ATLAS_PATH));






    }

    public static void finishLoading() {
        assetManager.finishLoading();
        grass1 = assetManager.get("grass1.png", Texture.class);
        grass2 = assetManager.get("grass2.png", Texture.class);
        sprite = assetManager.get("sprite.png", Texture.class);
        sprite2 = assetManager.get("sprite2.png", Texture.class);
        gate = assetManager.get("gate.png", Texture.class);
        nsprite = assetManager.get("nnpc1.png", Texture.class);
        tsprite = assetManager.get("TimeSprite.png", Texture.class);

    }

    public static void dispose() {
        assetManager.dispose();
    }
}







