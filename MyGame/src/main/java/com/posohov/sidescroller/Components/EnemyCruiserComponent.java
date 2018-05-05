package com.posohov.sidescroller.Components;


import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;
import com.posohov.baseengine.Scene;

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
                if (object.transform.x < object.scene.getCameraWidth() - moveDistance) {
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
                if (curY + shift > object.scene.getCameraHeight() - object.transform.height) {
                    shift = object.scene.getCameraHeight() - object.transform.height - curY;
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
