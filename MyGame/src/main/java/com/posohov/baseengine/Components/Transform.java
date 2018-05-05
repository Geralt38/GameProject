package com.posohov.baseengine.Components;


public class Transform extends GameComponent {

    public float x,y,xScale,yScale;
    public long rotation;
    public float width, height;

    public Transform(float x, float y, long rotation, float xScale, float yScale) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.xScale = xScale;
        this.yScale = yScale;
    }

    public Transform(float x, float y) {
        this(x,y,0,1,1);
    }

    public float getRight() {
        return x + width;
    }

    public float getBottom() {
        return y + height;
    }

    public void setDims(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setScale(float xScale, float yScale) {
        this.xScale = xScale;
        this.yScale = yScale;
        object.renderer.scaleSprite();
    }

    public void setScale(float scale) {
        setScale(scale, scale);
    }
}
