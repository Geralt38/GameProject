package com.posohov.sidescroller.Components;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;
import com.posohov.baseengine.Scene;

import course.labs.graphicslab.R;

public class PlayerComponent extends GameComponent {

    private int health;
    private int maxHealth;
    private float speed;
    private Scene.TouchInfo touchInfo;
    private Bitmap heartSprite;
    private int baseDamage;
    private int bonusDamage;
    private int maxBonusDamage;

    private int framesBeetweenShots = 10;
    private int framesToNextShot = 0;

    private Camera camera;

    public PlayerComponent(int speed, int health, int baseDamage, int maxBonusDamage) {
        this.baseDamage = baseDamage;
        this.bonusDamage = 0;
        this.maxBonusDamage = maxBonusDamage;
        this.maxHealth = health;
        this.health = health;
        this.speed = speed;
    }

    public void takeDamage(int damage) {
        bonusDamage = 0;
        health -= damage;
        if (health < 1) {
            GameControllerComponent gcc = object.scene.getGameController();
            if (gcc != null) {
                gcc.setPlayerDead();
                object.delete();
            } else {
                object.scene.restart();
            }
        }
    }

    public void heal(int healingAmount) {
        if (health != maxHealth) {
            health = Math.min(health + healingAmount, maxHealth);
        } else {
            addScore(100);
        }
    }

    public void buffDamage(int bonus) {
        if (bonusDamage != maxBonusDamage) {
            bonusDamage = Math.min(bonusDamage + bonus, maxBonusDamage);
        } else {
            addScore(100);
        }
    }

    private void addScore(int score) {
        GameControllerComponent gcc = object.scene.getGameController();
        if (gcc!=null) {
            gcc.addScore(score);
        }
    }

    @Override
    public void start() {
        heartSprite = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.heart), 33, 30, false);
        touchInfo = object.scene.getTouchInfo();
        camera = object.scene.getCamera();
    }

    @Override
    public void update() {
        if (touchInfo.touched) {
            moveTowards(camera.screenToGameWidth(touchInfo.x), camera.screenToGameHeight(touchInfo.y));
        }
        framesToNextShot--;
        if (framesToNextShot < 1) {
            framesToNextShot = framesBeetweenShots;
            spawnBullet();
        }
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        super.draw(canvas, camera);
        for (int i = 0; i < health; i++) {
            canvas.drawBitmap(heartSprite, 10 + i*35, 10, object.scene.getPaint());

            object.scene.getPaint().setColor(Color.WHITE);
            object.scene.getPaint().setTextSize(30);
            canvas.drawText("Damage: " + Integer.toString(baseDamage + bonusDamage), 200, 35, object.scene.getPaint());
        }
    }


    private void spawnBullet() {
        GameObject bullet = Prefabs.getSmallPlayerBullet(object.scene,0,0,baseDamage + bonusDamage);
        object.scene.addObjectDuringRuntime(bullet);
        bullet.transform.x = object.transform.x + object.transform.width;
        bullet.transform.y = object.transform.y + object.transform.height/2 - bullet.transform.height/2;
    }

    private void moveTowards(float x, float y) {
        float targetY = y - object.transform.height/2;
        float curY = object.transform.y;
        float distance = Math.abs(targetY - curY);
        float shift = (distance > speed) ? speed : distance;
        if (curY > targetY) {shift *= -1;}
        if (curY + shift < Scene.TOP_BAR_HEIGHT) { shift = Scene.TOP_BAR_HEIGHT - curY; }
        if (curY + shift > object.scene.getCameraHeight() - object.transform.height) { shift = object.scene.getCameraHeight() - object.transform.height - curY; }
        object.transform.y += shift;
    }
}
