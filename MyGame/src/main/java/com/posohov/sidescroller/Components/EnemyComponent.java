package com.posohov.sidescroller.Components;


import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.GameObject;
import com.posohov.sidescroller.Prefabs;

import java.util.Random;


public class EnemyComponent extends GameComponent {

    public int health;
    protected int score = 0;
    protected int collisionDamage = 1;
    protected float healthPowerUpChance = 0.3F;
    protected float damagePowerUpChance = 0.3F;

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 1) {
            destroy(true);
        }
    }

    protected void destroy(boolean byPlayer) {
        if (byPlayer) {
            GameControllerComponent gcc = object.scene.getGameController();
            if (gcc != null) {
                gcc.addScore(score);
            }
            dropPowerUp();
        }
        object.delete();
    }

    protected void dropPowerUp() {
        Random r = new Random();
        float ch = r.nextFloat();
        if (ch < healthPowerUpChance) {
            placePowerUp(Prefabs.getHealthPowerUp(object.scene,0,0));
        } else if (ch < healthPowerUpChance + damagePowerUpChance) {
            placePowerUp(Prefabs.getDamagePowerUp(object.scene,0,0));
        }
    }

    protected void placePowerUp(GameObject powerUp) {
        object.scene.addObjectDuringRuntime(powerUp);
        powerUp.transform.x = object.transform.x - powerUp.transform.width;
        powerUp.transform.y = object.transform.y + object.transform.height/2 - powerUp.transform.height/2;
    }

    @Override
    public void onCollision(GameObject collision) {
        PlayerComponent pc = (PlayerComponent) collision.getComponent(PlayerComponent.class);
        if (pc != null) {
            pc.takeDamage(collisionDamage);
            destroy(false);
        }
    }
}
