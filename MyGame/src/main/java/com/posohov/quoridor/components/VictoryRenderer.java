package com.posohov.quoridor.components;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;

public class VictoryRenderer extends GameComponent{

    float x;
    float y;
    float width;
    float height;
    float textWidth;
    int playerIndex;
    String message;
    Paint paint;
    Paint textPaint;

    public VictoryRenderer(float x, float y, float width, float height, int playerIndex) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playerIndex = playerIndex;
        this.message = "Player " + playerIndex + " Won!";
    }

    @Override
    public void start() {
        super.start();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        Camera camera = object.scene.getCamera();
        camera.setTextHeight(textPaint, height/2F);
        Rect bounds = new Rect();
        textPaint.getTextBounds(message, 0, message.length(), bounds);
        textWidth = bounds.width();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        super.draw(canvas, camera);
        camera.drawRect(canvas, x,y,width, height, paint);
        camera.drawText(canvas, (width - textWidth)/2F, y + height * 3/4, message, textPaint);
    }
}
