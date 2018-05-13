package com.posohov.quoridor.player;


import android.graphics.Bitmap;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.GridTouchListener;
import com.posohov.quoridor.PlayerTurnListener;

public class HumanPlayer extends Player implements GridTouchListener{

    public HumanPlayer(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        super(x, y, callback, grid, sprite);
    }

    @Override
    public void startTurn() {

    }

    @Override
    public void nodeTouched(int x, int y) {

    }

    @Override
    public void wallTouched(int x, int y, boolean isHorizontal) {

    }
}
