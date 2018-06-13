package com.posohov.quoridor.player;




public class Move {

    public int x;
    public int y;
    public int pastx;
    public int pasty;
    public boolean isHorizontal;
    public boolean isWall;
    public float value;

    public Move(int x, int y, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;
        isWall = true;
    }

    public Move(int x, int y, int pastx, int pasty) {
        this.x = x;
        this.y = y;
        this.pastx = pastx;
        this.pasty = pasty;
        isWall = false;
        isHorizontal = false;
    }
}
