package com.posohov.sidescroller.Components;


public class DamagePowerupComponent extends PowerupComponent {

    protected int damageBoost;

    public DamagePowerupComponent(float speed, int damage) {
        super(speed);
        this.damageBoost = damage;
    }

    @Override
    protected void applyPowerUp(PlayerComponent pc) {
        pc.buffDamage(damageBoost);
    }
}
