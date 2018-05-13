package com.posohov.sidescroller.scenes;

import android.content.Context;
import android.widget.RelativeLayout;

import com.posohov.baseengine.Scene;
import com.posohov.sidescroller.Prefabs;


public class GameScene extends Scene {

    public GameScene(Context context, RelativeLayout frame) {
        super(context, frame);
    }

    @Override
    protected void setupObjects() {
        super.setupObjects();
        addObject(Prefabs.getPlayer(this, 10, TOP_BAR_HEIGHT + 100));
        addObject(Prefabs.getMainEnemySpawner(this));
        addObject(Prefabs.getGameController(this));
    }
}
