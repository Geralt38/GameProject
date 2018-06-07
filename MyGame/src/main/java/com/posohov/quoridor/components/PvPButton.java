package com.posohov.quoridor.components;


import com.posohov.baseengine.GameActivity;

public class PvPButton extends Button {

    public PvPButton(float x, float y, float width, float height, String text, int spriteId) {
        super(x, y, width, height, text, spriteId);
    }

    @Override
    protected void onButtonPressed() {
        super.onButtonPressed();
        object.scene.switchScene(GameActivity.SceneType.PVP);
    }
}
