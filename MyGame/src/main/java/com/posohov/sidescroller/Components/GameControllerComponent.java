package com.posohov.sidescroller.Components;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;


public class GameControllerComponent extends GameComponent {

    private int score = 0;
    private boolean playerDead = false;
    private Paint paint;

    @Override
    public void update() {

    }

    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public void start() {
        paint = object.scene.getPaint();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        if (!playerDead) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(Integer.toString(score), object.scene.getCameraWidth() / 2, 35, paint);
        }
    }

    public void setPlayerDead() {
        object.scene.restart();
    }
}
