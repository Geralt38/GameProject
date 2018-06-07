package com.posohov.quoridor.components;


import com.posohov.baseengine.GameActivity;

public class PvAIButton extends Button{

    public PvAIButton(float x, float y, float width, float height, String text, int spriteId) {
        super(x, y, width, height, text, spriteId);
    }

    @Override
    protected void onButtonPressed() {
        super.onButtonPressed();
        object.scene.switchScene(GameActivity.SceneType.PVAI);
    }
}
