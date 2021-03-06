package com.posohov.baseengine;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Camera {

    private float width;
    private float height;
    private float screenWidth;
    private float screenHeight;
    private float gameToScreenMul;

    public Camera(float height, float screenWidth, float screenHeight) {
        this.height = height;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        gameToScreenMul = screenHeight/height;
        width = screenWidth / gameToScreenMul;
    }

    public float gameToScreenLength(float arg) {
        return arg * gameToScreenMul;
    }

    public float screenToGameLength(float arg) {return arg / gameToScreenMul;}

    public void drawSprite(Canvas canvas, Bitmap sprite, float x, float y, long rotation, Paint paint) {
        canvas.save();

        canvas.rotate((float)(rotation), x + sprite.getWidth()/2, y + sprite.getHeight()/2);
        canvas.drawBitmap(sprite, gameToScreenLength(x), gameToScreenLength(y), paint);

        canvas.restore();
    }

    public void drawSprite(Canvas canvas, Bitmap sprite, float x, float y, Paint paint) {
        drawSprite(canvas, sprite, x, y, 0, paint);
    }

    public void drawText(Canvas canvas, float x, float y, String text, Paint paint) {
        canvas.drawText(text, gameToScreenLength(x), gameToScreenLength(y), paint);
    }

    public void fillScreen(Canvas canvas, Paint paint) {
        canvas.drawRect(0, 0, screenWidth, screenHeight, paint);
    }

    public void drawRect(Canvas canvas, float x, float y, float width, float height, Paint paint) {
        canvas.drawRect(gameToScreenLength(x), gameToScreenLength(y), gameToScreenLength(x + width), gameToScreenLength(y + height), paint);
    }

    public void setTextHeight(Paint paint, float textHeight) {
        textHeight = gameToScreenLength(textHeight);
        paint.setTextSize(20f);
        Rect bounds = new Rect();
        float newSize;
        paint.getTextBounds("10", 0, 1, bounds);
        newSize = textHeight / (float)bounds.height() * 20f;
        paint.setTextSize(newSize);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
