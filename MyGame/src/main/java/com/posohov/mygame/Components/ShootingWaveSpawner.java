package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;

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
        GameObject enemy = Prefabs.getShootingEnemy(object.scene, object.scene.getDisplayWidth(), 0, 5, 10);
        placeEnemy(enemy);
    }


}
