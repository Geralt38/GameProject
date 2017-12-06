package com.posohov.mygame.Components;


import com.posohov.mygame.GameActivity;
import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;
import com.posohov.mygame.Scene;

import java.util.Random;

import course.labs.graphicslab.R;

public class SpawnerComponent extends GameComponent{

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
        enemy.transform.y = r.nextFloat() * (object.scene.getDisplayHeight() - enemy.transform.height - Scene.TOP_BAR_HEIGHT) + Scene.TOP_BAR_HEIGHT;
    }
}
