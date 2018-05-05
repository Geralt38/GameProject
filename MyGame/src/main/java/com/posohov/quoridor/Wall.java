package com.posohov.quoridor;


public class Wall {

    public final int x;
    public final int y;
    private boolean blocked;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.blocked = false;
    }

    public void block() {blocked = true;}

    public boolean isBlocked() {return blocked;}
}
