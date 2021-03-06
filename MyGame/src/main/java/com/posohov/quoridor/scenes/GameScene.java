package com.posohov.quoridor.scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.posohov.baseengine.GameActivity;
import com.posohov.baseengine.GameObject;
import com.posohov.baseengine.Scene;
import com.posohov.quoridor.Prefabs;


public class GameScene extends Scene {

    private GameType gameType;

    public GameScene(Context context, RelativeLayout frame, GameType gameType) {
        super(context, frame);
        this.gameType = gameType;
    }

    @Override
    protected void setupObjects() {
        super.setupObjects();
        addObject(Prefabs.getGridController(this, gameType));
    }

    @Override
    protected synchronized void drawScene(Canvas canvas) {


        //mPainter.setColor(Color.rgb(162,67,58));
        mPainter.setColor(Color.rgb(62,42,26));
        if (camera != null) {
            camera.fillScreen(canvas, mPainter);
        }
        super.drawScene(canvas);
    }

    @Override
    public void onBackPressed() {
        switchScene(GameActivity.SceneType.MENU);
    }

    public enum GameType {PVP, PVAI}
}
