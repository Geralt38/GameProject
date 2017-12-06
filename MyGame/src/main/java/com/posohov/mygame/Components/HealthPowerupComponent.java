package com.posohov.mygame.Components;



public class HealthPowerupComponent extends PowerupComponent {

    private int healingAmount = 1;

    public HealthPowerupComponent(float speed) {
        super(speed);
    }

    @Override
    protected void applyPowerUp(PlayerComponent pc) {
        pc.heal(healingAmount);
    }
}
