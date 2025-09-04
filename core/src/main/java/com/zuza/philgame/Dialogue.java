package com.zuza.philgame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

// This class was created with the help from AI

public class Dialogue extends Table {
    private final Window window;
    private final Label text;
    private boolean showing;

    public Dialogue(Skin skin) {
        setFillParent(true);

        window = new Window("", skin, "dialog");
        window.setMovable(false);
        window.pad(12f);

        text = new Label("", skin, "default");
        text.setWrap(true);
        window.add(text).grow().width(600f);

        add().expand().row();
        add(window).expandX().bottom().pad(16f);

        window.setVisible(false);

        // click to dismiss when not in use
        window.addListener(new ClickListener() {
            @Override public void clicked(InputEvent e, float x, float y) { hide(); }
        });
    }

    public void show(String line) {
        text.setText(line);
        window.setVisible(true);
        showing = true;
    }
    public void hide() { window.setVisible(false); showing = false; }
    public boolean isShowing() { return showing; }
}


