package com.posohov.quoridor.components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.Scene;


public class Button extends GameComponent {

    private float x;
    private float y;
    private float width;
    private float height;
    private float textWidth;
    private int spriteId;
    private String text;
    private Scene.TouchInfo touchInfo;
    private Paint paint;
    private Bitmap sprite;

    public Button(float x, float y, float width, float height, String text, int spriteId) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.spriteId = spriteId;
    }

    @Override
    public void start() {
        super.start();
        touchInfo = object.scene.getTouchInfo();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        Camera camera = object.scene.getCamera();
        camera.setTextHeight(paint, height/3F);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        textWidth = bounds.width();

        sprite = BitmapFactory.decodeResource(object.scene.getResources(), spriteId);
        sprite = Bitmap.createScaledBitmap(sprite,
                (int) camera.gameToScreenLength(width),
                (int) camera.gameToScreenLength(height), false);
    }

    @Override
    public void update() {
        super.update();
        if (touchInfo.newTouch && touchInfo.inRect(x,y,width,height) ) {
            onButtonPressed();
        }
    }

    protected void onButtonPressed() {

    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        super.draw(canvas, camera);
        camera.drawSprite(canvas, sprite, x, y, paint);
        camera.drawText(canvas, x + (width - textWidth)/2F + 50F, y + height * 2/3, text, paint);
    }
}
