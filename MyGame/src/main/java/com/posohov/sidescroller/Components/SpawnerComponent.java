package com.posohov.sidescroller.Components;


import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.GameObject;
import com.posohov.baseengine.Scene;

import java.util.Random;

public class SpawnerComponent extends GameComponent {

    protected int framesBetweenEnemies = 20;
    protected int framesTillNextSpawn = 10;
    protected Random r;

    @Override
    public void start() {
        r = new Random();
    }

    @Override
    public void update() {
        framesTillNextSpawn--;
        if (framesTillNextSpawn < 1) {
            framesTillNextSpawn = framesBetweenEnemies;
            spawn();
        }
    }

    protected void spawn() {

    }

    protected void placeEnemy(GameObject enemy) {
        object.scene.addObjectDuringRuntime(enemy);
        enemy.transform.y = r.nextFloat() * (object.scene.getCameraHeight() - enemy.transform.height - Scene.TOP_BAR_HEIGHT) + Scene.TOP_BAR_HEIGHT;
    }
}
