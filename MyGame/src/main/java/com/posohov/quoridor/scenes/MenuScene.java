package com.posohov.quoridor.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.posohov.baseengine.Scene;
import com.posohov.quoridor.Prefabs;


public class MenuScene extends Scene {

    public MenuScene(Context context, RelativeLayout frame) {super(context, frame);}

    @Override
    protected void setupObjects() {
        super.setupObjects();
        float buttonWidth = camera.getWidth()/2F;
        float buttonHeight = buttonWidth/4F;
        float x = (camera.getWidth() - buttonWidth)/2F;
        addObject(Prefabs.getPvPButton(this, x, camera.getHeight()/2F - buttonHeight - 30F, buttonWidth, buttonHeight));
        addObject(Prefabs.getPvAIButton(this, x, camera.getHeight()/2F + 30F, buttonWidth, buttonHeight));
    }

    @Override
    protected synchronized void drawScene(Canvas canvas) {


        //mPainter.setColor(Color.rgb(162,67,58));
        mPainter.setColor(Color.rgb(162,67,58));
        if (camera != null) {
            camera.fillScreen(canvas, mPainter);
        }
        super.drawScene(canvas);
    }
}
