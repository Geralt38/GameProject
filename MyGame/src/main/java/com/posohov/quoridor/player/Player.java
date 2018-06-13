package com.posohov.quoridor.player;


import android.graphics.Bitmap;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.PlayerTurnListener;

public class Player {

    public int x;
    public int y;
    protected int startingRow;
    protected Grid grid;
    protected PlayerTurnListener callback;
    protected Bitmap sprite;
    public int wallNumber;

    public Player(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        this.x = x;
        this.y = y;
        this.startingRow = y;
        this.callback = callback;
        this.grid = grid;
        this.sprite = sprite;
        wallNumber = 10;
    }

    public void startTurn() {

    }

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
