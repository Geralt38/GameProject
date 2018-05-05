package com.posohov.sidescroller.Components;


import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;

public class ContinuousSpawnerComponent extends SpawnerComponent {

    private float shootingChance = 0.3F;

    @Override
    public void spawn() {
        float ch = r.nextFloat();
        GameObject enemy;
        if (ch < shootingChance) {
            enemy = Prefabs.getShootingEnemy(object.scene, object.scene.getCameraWidth(), 0, 5, 10);
        } else {
            enemy = Prefabs.getSimpleEnemy(object.scene, object.scene.getCameraWidth(), 0, r.nextFloat()*4 + 3, 5, 10);
        }
        placeEnemy(enemy);
    }
}
