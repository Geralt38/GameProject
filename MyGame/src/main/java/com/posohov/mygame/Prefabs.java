package com.posohov.mygame;

import com.posohov.mygame.Components.DamagePowerupComponent;
import com.posohov.mygame.Components.EnemyBulletComponent;
import com.posohov.mygame.Components.EnemyCruiserComponent;
import com.posohov.mygame.Components.GameComponent;
import com.posohov.mygame.Components.GameControllerComponent;
import com.posohov.mygame.Components.HealthPowerupComponent;
import com.posohov.mygame.Components.MainEnemySpawnerComponent;
import com.posohov.mygame.Components.PlayerBulletComponent;
import com.posohov.mygame.Components.PlayerComponent;
import com.posohov.mygame.Components.ShootingEnemyComponent;
import com.posohov.mygame.Components.SimpleEnemyComponent;

import course.labs.graphicslab.R;

public class Prefabs {

    public static GameObject getPlayer(Scene scene, float x, float y) {
        return new GameObject(scene, "player", R.drawable.player, x, y, 0, 2, 2).addComponent(new PlayerComponent(15, 3, 2, 2));
    }

    public static GameObject getSmallPlayerBullet(Scene scene, float x, float y, int damage) {
        return new GameObject(scene, "playerbullet", R.drawable.playerbullet, x, y, 0, 2, 2).addComponent(new PlayerBulletComponent(damage,40));
    }

    public static GameObject getEnemyBullet(Scene scene, float x, float y) {
        return new GameObject(scene, "enemybullet", R.drawable.enemybullet, x, y, 0, 4, 4).addComponent(new EnemyBulletComponent(1,30));
    }

    public static GameObject getBigBullet(Scene scene, float x, float y) {
        return new GameObject(scene, "bigenemybullet", R.drawable.bigbullet, x, y, 0, 2, 2).addComponent(new EnemyBulletComponent(1,30));
    }

    public static GameObject getSimpleEnemy(Scene scene, float x, float y, float speed, int health, int score) {
        return new GameObject(scene, "simpleenemy", R.drawable.simpleenemy, x, y, 0, 2, 2).addComponent(new SimpleEnemyComponent(speed,health,score));
    }

    public static GameObject getShootingEnemy(Scene scene, float x, float y, int health, int score) {
        return new GameObject(scene, "shootingenemy", R.drawable.enemyshooting, x, y, 0, 2, 2).addComponent(new ShootingEnemyComponent(health,score));
    }

    public static GameObject getCruiserEnemy(Scene scene, float x, float y, int health, int score) {
        return new GameObject(scene, "cruiserenemy", R.drawable.cruiser, x, y, 0, 1, 1).addComponent(new EnemyCruiserComponent(health,score));
    }

    public static GameObject getHealthPowerUp(Scene scene, float x, float y) {
        return new GameObject(scene, "healthpowerup", R.drawable.powerupgreen, x, y, 0, 4, 4).addComponent(new HealthPowerupComponent(20));
    }

    public static GameObject getDamagePowerUp(Scene scene, float x, float y) {
        return new GameObject(scene, "damagepowerup", R.drawable.powerupblue, x, y, 0, 4, 4).addComponent(new DamagePowerupComponent(20, 1));
    }

    public static GameObject getMainEnemySpawner(Scene scene) {
        return new GameObject(scene, "mainenemyspawner").addComponent(new MainEnemySpawnerComponent());
    }

    public static GameObject getGameController(Scene scene) {
        return new GameObject(scene, "gamecontroller").addComponent(new GameControllerComponent());
    }
}
