package com.posohov.baseengine;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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
        Log.d("cam ww", Float.toString(width));
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
