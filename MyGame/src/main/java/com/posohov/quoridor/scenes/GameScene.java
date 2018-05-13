package com.posohov.quoridor.scenes;

import android.content.Context;
import android.widget.RelativeLayout;

import com.posohov.baseengine.Scene;
import com.posohov.quoridor.Prefabs;


public class GameScene extends Scene {

    public GameScene(Context context, RelativeLayout frame) {
        super(context, frame);
    }

    @Override
    protected void setupObjects() {
        super.setupObjects();
        addObject(Prefabs.getGridController(this));
    }
}
