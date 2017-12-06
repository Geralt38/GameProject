package com.posohov.mygame.Components;

import com.posohov.mygame.GameObject;

public class PowerupComponent extends GameComponent {

    private float speed;

    public PowerupComponent(float speed) {
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
        applyPowerUp(pc);
        object.delete();
    }

    protected void applyPowerUp(PlayerComponent pc) {

    }
}
