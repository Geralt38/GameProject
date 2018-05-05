package com.posohov.sidescroller.Components;


import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.GameObject;

public class PlayerBulletComponent extends GameComponent {

    private int damage;
    private float speed;

    public PlayerBulletComponent(int damage, float speed) {
        this.damage = damage;
        this.speed = speed;
    }

    @Override
    public void update() {
        object.transform.x += speed;
        if (object.transform.x > object.scene.getCameraWidth()) {
            object.delete();
        }
    }

    @Override
    public void onCollision(GameObject collision) {
        EnemyComponent ec = (EnemyComponent) collision.getComponent(EnemyComponent.class);
        if (ec == null) {
            return;
        }
        ec.takeDamage(damage);
        object.delete();
    }
}
