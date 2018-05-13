package com.posohov.quoridor.player;


import android.graphics.Bitmap;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.PlayerTurnListener;

public class AIPlayer extends Player {

    public AIPlayer(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        super(x, y, callback, grid, sprite);
    }

    @Override
    public void startTurn() {

    }
}
