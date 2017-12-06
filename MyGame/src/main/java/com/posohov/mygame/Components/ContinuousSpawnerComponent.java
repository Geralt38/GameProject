package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;

public class ContinuousSpawnerComponent extends SpawnerComponent {

    private float shootingChance = 0.3F;

    @Override
    public void spawn() {
        float ch = r.nextFloat();
        GameObject enemy;
        if (ch < shootingChance) {
            enemy = Prefabs.getShootingEnemy(object.scene, object.scene.getDisplayWidth(), 0, 5, 10);
        } else {
            enemy = Prefabs.getSimpleEnemy(object.scene, object.scene.getDisplayWidth(), 0, r.nextFloat()*4 + 3, 5, 10);
        }
        placeEnemy(enemy);
    }
}
