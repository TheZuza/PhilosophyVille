package com.zuza.philgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameState {

    private int points = 100; //start with 100 points

    public int getPoints() {
        return points;
    }
    public void addPoints(int delta) {points += delta;}
    public void setPoints(int delta) {points = delta;}


    // for saves
    public void save() {
        Preferences p = Gdx.app.getPreferences("save");
        p.putInteger("points", points);
        p.flush();
    }
    public void load() {
        Preferences p = Gdx.app.getPreferences("save");
        points = p.getInteger("points", 0);

    }


}
