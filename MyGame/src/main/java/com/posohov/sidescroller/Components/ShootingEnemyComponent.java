package com.posohov.sidescroller.Components;


import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;

import java.util.Random;

public class ShootingEnemyComponent extends EnemyComponent {

    private float maxSpeed = 40;
    private float minStartingSpeed = 20;
    private float slowingSpeed = 1.5F;
    private float acceleration = 2;
    private float speed;

    private int shotsToFire = 3;
    private int shotsFired = 0;
    private int framesBeetweenShots = 10;
    private int framesTillNextShot = 0;

    public enum State { SLOWING, SHOOTING, EXITING}

    private State state;

    public ShootingEnemyComponent(int health, int score) {
        this.health = health;
        this.score = score;
        state = State.SLOWING;
        Random r = new Random();
        speed = r.nextFloat() * (maxSpeed - minStartingSpeed) + minStartingSpeed;
    }

    @Override
    public void start() {
        damagePowerUpChance = 0.5F;
        healthPowerUpChance = 0.5F;
    }

    @Override
    public void update() {
        switch (state) {
            case SLOWING:
                speed = Math.max(speed - slowingSpeed, 0);
                if (speed == 0) {
                    state = State.SHOOTING;
                    framesTillNextShot = framesBeetweenShots;
                } else {
                    object.transform.x -= speed;
                }
                break;
            case SHOOTING:
                framesTillNextShot--;
                if (framesTillNextShot < 1) {
                    shoot();
                    shotsFired++;
                    framesTillNextShot = framesBeetweenShots;
                    if (shotsFired >= shotsToFire) {
                        state = State.EXITING;
                    }
                }
                break;
            case EXITING:
                speed = Math.min(speed + acceleration, maxSpeed);
                object.transform.x -= speed;
                break;
        }
        if (object.transform.x < - object.transform.width) {
            destroy(false);
        }
    }

    private void shoot() {
        GameObject bullet = Prefabs.getEnemyBullet(object.scene,0,0);
        object.scene.addObjectDuringRuntime(bullet);
        bullet.transform.x = object.transform.x - bullet.transform.width;
        bullet.transform.y = object.transform.y + object.transform.height/2 - bullet.transform.height/2;
    }
}
