package com.posohov.mygame;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.posohov.mygame.Components.GameComponent;
import com.posohov.mygame.Components.Renderer;
import com.posohov.mygame.Components.Transform;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import course.labs.graphicslab.R;

public class GameObject {

    public Scene scene;

    public Transform transform;
    public Renderer renderer;
    public String tag;


    private CopyOnWriteArrayList<GameComponent> components = new CopyOnWriteArrayList<GameComponent>();

    public GameObject(Scene scene, String tag) {
        this.scene = scene;
        this.tag = tag;
    }

    public GameObject(Scene scene, String tag, int spriteId, float x, float y, long rotation, float xScale, float yScale) {
        this.scene = scene;
        this.tag = tag;
        addRenderer(scene.getPaint(), spriteId);
        addTransform(x,y,rotation,xScale,yScale);
    }

    public GameObject(Scene scene, String tag, int spriteId, float x, float y) {
        this(scene, tag,spriteId,x,y,0,1,1);
    }

    public GameObject addTransform(float x, float y, long rotation, float xScale, float yScale) {
        transform = new Transform(x,y,rotation,xScale,yScale);
        addComponent(transform);
        return this;
    }

    public GameObject addRenderer(Paint paint, int spriteId) {
        renderer = new Renderer(paint, spriteId);
        addComponent(renderer);
        return this;
    }

    public GameObject addComponent(GameComponent component) {
        component.initialize(this);
        components.add(component);
        return this;
    }

    public void addComponentDuringRuntime(GameComponent component) {
        addComponent(component);
        component.start();
    }

    public void deleteComponent(GameComponent component) {
        components.remove(component);
    }

    public GameComponent getComponent(Class<?> cls) {
        for (GameComponent component: components) {
            if (cls.isInstance(component)) {
                return component;
            }
        }
        return null;
    }

    public void start() {
        for (GameComponent component : components) {
            component.start();
        }
    }

    public void update() {
        for (GameComponent component : components) {
            component.update();
        }
    }

    public void draw(Canvas canvas) {
        canvas.save();
        for (GameComponent component : components) {
            component.draw(canvas);
        }
        canvas.restore();
    }

    public void onCollision(GameObject collision) {
        for (GameComponent component : components) {
            component.onCollision(collision);
        }
    }

    public void delete() {
        scene.deleteObject(this);
    }
}
