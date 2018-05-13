package com.posohov.quoridor;


import com.posohov.baseengine.GameObject;
import com.posohov.baseengine.Scene;
import com.posohov.quoridor.components.GridController;

import course.labs.graphicslab.R;

public class Prefabs {

    public static GameObject getGridController(Scene scene) {
        return new GameObject(scene, "grid_controller").addComponent(new GridController());
    }
}
