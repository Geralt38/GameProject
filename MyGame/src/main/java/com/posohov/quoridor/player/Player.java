package com.posohov.quoridor.player;


import android.graphics.Bitmap;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.PlayerTurnListener;

public abstract class Player {

    protected int x;
    protected int y;
    protected int startingRow;
    protected Grid grid;
    protected PlayerTurnListener callback;
    protected Bitmap sprite;
    protected int wallNumber;

    public Player(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        this.x = x;
        this.y = y;
        this.startingRow = y;
        this.callback = callback;
        this.grid = grid;
        this.sprite = sprite;
        wallNumber = 10;
    }

    public abstract void startTurn();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public int getStartingRow() {
        return startingRow;
    }

    public int getWallNumber() {
        return wallNumber;
    }
}
