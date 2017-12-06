package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;

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
        if (object.transform.x > object.scene.getDisplayWidth()) {
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
