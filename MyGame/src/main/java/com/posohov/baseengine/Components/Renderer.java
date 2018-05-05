package com.posohov.baseengine.Components;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.posohov.baseengine.Camera;

public class Renderer extends GameComponent {

    private Paint painter;
    private Transform transform;
    private Camera camera;

    private Bitmap sprite;
    private Bitmap spriteScaled;
    private int spriteId;

    public Renderer(Paint paint, int spriteId) {
        this.spriteId = spriteId;
        painter = paint;
    }

    public void scaleSprite() {
            spriteScaled = Bitmap.createScaledBitmap(sprite,
                    (int) camera.gameToScreenLength(sprite.getWidth() * transform.xScale),
                    (int) camera.gameToScreenLength(sprite.getHeight() * transform.yScale), false);
            object.transform.setDims(sprite.getWidth() * transform.xScale, sprite.getHeight() * transform.yScale);
    }

    @Override
    public void start() {
        camera = object.scene.getCamera();
        sprite = BitmapFactory.decodeResource(object.scene.getResources(), spriteId);
        transform = object.transform;
        scaleSprite();
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {

        camera.drawSprite(canvas, spriteScaled, transform.x, transform.y, transform.rotation, painter);
        /*canvas.save();

        canvas.rotate((float)(transform.rotation), transform.x + transform.width/2, transform.y + transform.height/2);
        canvas.drawBitmap(spriteScaled, transform.x, transform.y, painter);

        canvas.restore();*/
    }
}
