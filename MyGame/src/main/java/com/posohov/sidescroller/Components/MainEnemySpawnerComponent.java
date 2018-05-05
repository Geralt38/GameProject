package com.posohov.sidescroller.Components;


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
