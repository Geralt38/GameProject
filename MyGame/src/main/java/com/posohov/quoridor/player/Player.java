package com.posohov.quoridor.player;


import android.graphics.Bitmap;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.PlayerTurnListener;

public abstract class Player {

    private int x;
    private int y;
    private int startingRow;
    private Grid grid;
    private PlayerTurnListener callback;
    private Bitmap sprite;

    public Player(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        this.x = x;
        this.y = y;
        this.startingRow = y;
        this.callback = callback;
        this.grid = grid;
        this.sprite = sprite;
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
}
