package com.posohov.mygame.Components;


import com.posohov.mygame.GameObject;

public class EnemyBulletComponent extends GameComponent {

    private int damage;
    private float speed;

    public EnemyBulletComponent(int damage, float speed) {
        this.damage = damage;
        this.speed = speed;
    }

    @Override
    public void update() {
        object.transform.x -= speed;
        if (object.transform.x < -object.transform.x) {
            object.delete();
        }
    }

    @Override
    public void onCollision(GameObject collision) {
        PlayerComponent pc = (PlayerComponent) collision.getComponent(PlayerComponent.class);
        if (pc == null) {
            return;
        }
        pc.takeDamage(damage);
        object.delete();
    }

}
