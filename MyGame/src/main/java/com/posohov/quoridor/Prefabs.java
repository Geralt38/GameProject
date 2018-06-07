package com.posohov.quoridor;


import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.GameObject;
import com.posohov.baseengine.Scene;
import com.posohov.quoridor.components.GridController;
import com.posohov.quoridor.components.PvAIButton;
import com.posohov.quoridor.components.PvPButton;
import com.posohov.quoridor.components.VictoryRenderer;
import com.posohov.quoridor.scenes.GameScene;

import course.labs.graphicslab.R;

public class Prefabs {

    public static GameObject getGridController(Scene scene, GameScene.GameType gameType) {
        return new GameObject(scene, "grid_controller").addComponent(new GridController(gameType));
    }

    public static GameObject getVictoryRenderer(Scene scene, float x, float y, float width, float height, int playerIndex) {
        return new GameObject(scene, "victory_renderer").addComponent(new VictoryRenderer(x,y,width,height, playerIndex));
    }

    public static GameObject getPvPButton(Scene scene, float x, float y, float width, float height) {
        return new GameObject(scene, "button_pvp").addComponent(new PvPButton(x,y,width,height,"Two Players", R.drawable.button_yellow));
    }

    public static GameObject getPvAIButton(Scene scene, float x, float y, float width, float height) {
        return new GameObject(scene, "button_pvai").addComponent(new PvAIButton(x,y,width,height,"Player Vs AI", R.drawable.button_yellow));
    }
}
