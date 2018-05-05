package com.posohov.sidescroller.Components;


import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;

public class ShootingWaveSpawner extends SpawnerComponent {

    private int enemiesToSpawn = 20;
    private int enemiesSpawned = 0;

    public ShootingWaveSpawner(int framesBeetweenSpawns, int enemiesToSpawn) {
        this.framesBetweenEnemies = framesBeetweenSpawns;
        this.enemiesToSpawn = enemiesToSpawn;
    }

    @Override
    public void spawn() {
        if (enemiesSpawned >= enemiesToSpawn) {
            delete();
            return;
        }
        enemiesSpawned++;
        GameObject enemy = Prefabs.getShootingEnemy(object.scene, object.scene.getCameraWidth(), 0, 5, 10);
        placeEnemy(enemy);
    }


}
