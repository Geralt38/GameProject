package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;
import com.posohov.mygame.Prefabs;
import com.posohov.mygame.Scene;

import java.util.Random;

public class EnemyCruiserComponent extends EnemyComponent {

    private float moveDistance = 400;
    private float speed = 2;
    private int dir = 1;
    private int framesBeetweenShots = 20;
    private int framesTillShoot = 10;

    public enum State { MOVING, FLOATING}

    private State state;

    public EnemyCruiserComponent(int health, int score) {
        this.health = health;
        this.score = score;
        state = State.MOVING;
    }

    @Override
    public void start() {
        healthPowerUpChance = 0F;
        damagePowerUpChance = 1F;
    }

    @Override
    public void update() {
        switch (state) {
            case MOVING:
                object.transform.x -= speed;
                if (object.transform.x < object.scene.getDisplayWidth() - moveDistance) {
                    state = State.FLOATING;
                }
                break;
            case FLOATING:
                float curY = object.transform.y;
                float shift = speed * dir;
                if (curY + shift < Scene.TOP_BAR_HEIGHT) {
                    shift = Scene.TOP_BAR_HEIGHT - curY;
                    dir = 1;
                }
                if (curY + shift > object.scene.getDisplayHeight() - object.transform.height) {
                    shift = object.scene.getDisplayHeight() - object.transform.height - curY;
                    dir = -1;
                }
                object.transform.y += shift;
                framesTillShoot--;
                if (framesTillShoot < 1) {
                    shoot();
                    framesTillShoot = framesBeetweenShots;
                }
                break;
        }
    }

    private void shoot() {
        GameObject bullet = Prefabs.getBigBullet(object.scene,0,0);
        object.scene.addObjectDuringRuntime(bullet);
        bullet.transform.x = object.transform.x - bullet.transform.width;
        bullet.transform.y = object.transform.y + object.transform.height/3 - bullet.transform.height/2;
        bullet = Prefabs.getBigBullet(object.scene,0,0);
        object.scene.addObjectDuringRuntime(bullet);
        bullet.transform.x = object.transform.x - bullet.transform.width;
        bullet.transform.y = object.transform.y + object.transform.height * 2/3 - bullet.transform.height/2;
    }
}
