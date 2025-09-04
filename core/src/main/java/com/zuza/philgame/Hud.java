package com.zuza.philgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;


public class Hud extends Table {
    private final GameState state;
    private final Label pointsLabel;

    public Hud(Skin skin, GameState state) {
        super();
        this.state = state;
        setFillParent(true);
        top().left().pad(6);




                Label.LabelStyle style = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
                pointsLabel = new Label("", style);


                // Ai helped with this: wraps in a container that owns the background. Used for better visibility

                Container<Label> badge = new Container<>(pointsLabel);
                badge.setBackground(skin.newDrawable("white", new Color(0f, 0.5f, 0f, 0.4f)));
                badge.pad(4, 8, 4, 8);


                add(badge).left().top();

                updateText();
            }




    private void updateText() {
        pointsLabel.setText("Points: " + state.getPoints());
    }

    @Override public void act(float delta) {
        super.act(delta);

        updateText();
    }
}

