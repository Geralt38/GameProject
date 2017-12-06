package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;
import com.posohov.mygame.Scene;

import java.util.Random;

public class MainEnemySpawnerComponent extends SpawnerComponent {


    private int framesTillShoterWave = 200;

    @Override
    public void start() {
        super.start();
        object.addComponentDuringRuntime(new ContinuousSpawnerComponent());
        object.addComponentDuringRuntime(new CruiserSpawnerComponent());
    }

    @Override
    public void update() {
        framesTillShoterWave--;
        if (framesTillShoterWave < 1) {
            framesTillShoterWave = r.nextInt(400) + 400;
            object.addComponentDuringRuntime(new ShootingWaveSpawner(20, 10));
        }
    }
}