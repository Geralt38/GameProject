package com.posohov.quoridor;


public class Wall {

    public final int x;
    public final int y;
    private boolean blocked;
    private boolean highlighted;
    private final boolean horizontal;

    public Wall(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.blocked = false;
        this.horizontal = horizontal;
    }

    public Wall(int x, int y, boolean blocked, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
        this.horizontal = horizontal;
    }

    public void block() {blocked = true;}

    public boolean isBlocked() {return blocked;}

    public void highlight() {highlighted = true;}

    public void dehighlight() {highlighted = false;}

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
