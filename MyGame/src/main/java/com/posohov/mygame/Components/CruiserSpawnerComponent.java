package com.posohov.mygame.Components;


import android.util.Log;

import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;

public class CruiserSpawnerComponent extends SpawnerComponent {

    @Override
    public void start() {
        super.start();
        framesTillNextSpawn = 400;
        framesBetweenEnemies = 800;
    }

    @Override
    public void spawn() {
        Log.v("Tag", "spawned");
        GameObject enemy;
        enemy = Prefabs.getCruiserEnemy(object.scene, object.scene.getDisplayWidth(), 0, 40, 100);
        placeEnemy(enemy);
    }
}
