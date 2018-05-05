package com.posohov.baseengine.Components;


import android.graphics.Canvas;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.GameObject;

public abstract class GameComponent {

    public GameObject object;

    public void initialize(GameObject object) {
        this.object = object;
    }

    public void start() {

    }

    public void update() {

    }

    public void draw(Canvas canvas, Camera camera) {

    }

    public void onCollision(GameObject collision) {

    }

    public void delete() {
        object.deleteComponent(this);
    }
}
