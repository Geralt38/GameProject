package com.posohov.mygame.Components;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import course.labs.graphicslab.R;

public class Renderer extends GameComponent {

    private Paint painter;
    private Transform transform;

    private Bitmap sprite;
    private Bitmap spriteScaled;
    private int spriteId;

    public Renderer(Paint paint, int spriteId) {
        this.spriteId = spriteId;
        painter = paint;
    }

    public void scaleSprite() {
            spriteScaled = Bitmap.createScaledBitmap(sprite, (int) (sprite.getWidth() * transform.xScale), (int) (sprite.getHeight() * transform.yScale), false);
            object.transform.setDims(spriteScaled.getWidth(), spriteScaled.getHeight());
    }

    @Override
    public void start() {
        sprite = BitmapFactory.decodeResource(object.scene.getResources(), spriteId);
        transform = object.transform;
        scaleSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();

        canvas.rotate((float)(transform.rotation), transform.x + transform.width/2, transform.y + transform.height/2);
        canvas.drawBitmap(spriteScaled, transform.x, transform.y, painter);

        canvas.restore();
    }
}
