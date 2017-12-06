package com.posohov.mygame.Components;


import android.graphics.Canvas;

import com.posohov.mygame.GameObject;

public abstract class GameComponent {

    public GameObject object;

    public void initialize(GameObject object) {
        this.object = object;
    }

    public void start() {

    }

    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public void onCollision(GameObject collision) {

    }

    public void delete() {
        object.deleteComponent(this);
    }
}
