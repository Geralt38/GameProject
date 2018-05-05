package com.posohov.sidescroller.Components;



public class SimpleEnemyComponent extends EnemyComponent{

    private float speed;

    public SimpleEnemyComponent(float speed, int health, int score) {
        this.health = health;
        this.speed = speed;
        this.score = score;
    }

    @Override
    public void update() {
        object.transform.x -= speed;
        if (object.transform.x < - object.transform.width) {
            destroy(false);
        }
    }
}
