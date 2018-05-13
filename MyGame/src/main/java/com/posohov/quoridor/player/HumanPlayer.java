package com.posohov.quoridor.player;


import android.graphics.Bitmap;
import android.util.Log;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.GridTouchListener;
import com.posohov.quoridor.Node;
import com.posohov.quoridor.PlayerTurnListener;
import com.posohov.quoridor.Wall;

import java.util.List;

public class HumanPlayer extends Player implements GridTouchListener{

    private TurnState turnState;
    private Wall selectedWall;

    public HumanPlayer(int x, int y, PlayerTurnListener callback, Grid grid, Bitmap sprite) {
        super(x, y, callback, grid, sprite);
        turnState = TurnState.STARTED;
    }

    @Override
    public void startTurn() {

    }

    @Override
    public void nodeTouched(int x, int y) {
        switch (turnState) {
            case STARTED:
                if ((this.x == x) && (this.y == y)) {
                    highlightNodes();
                    turnState = TurnState.NODESSELECTED;
                }
                break;
            case NODESSELECTED:
                if (grid.getNodes()[x][y].isHighlighted()) {
                    this.x = x;
                    this.y = y;
                    turnState = TurnState.STARTED;
                    grid.dehighlightEverything();
                    callback.onPlayerTurnEnd();
                }
                break;
            case WALLSSELECTED:
                if ((this.x == x) && (this.y == y)) {
                    grid.dehighlightEverything();
                    highlightNodes();
                    turnState = TurnState.NODESSELECTED;
                }
                break;
        }

    }

    private void highlightNodes() {
        List<Node> nodes = grid.getPossibleMoveLocations(x,y);
        for (Node node : nodes) {
            node.highlight();
        }
    }

    @Override
    public void wallTouched(int x, int y, boolean isHorizontal) {
        Wall wall = grid.getWall(x,y,isHorizontal);
        switch (turnState) {

            case WALLSSELECTED:
                if (wall.isHighlighted()) {
                    grid.blockWalls(selectedWall, wall);
                    grid.dehighlightEverything();
                    turnState = TurnState.STARTED;
                    break;
                }
            case NODESSELECTED:
                grid.dehighlightEverything();
                turnState = TurnState.STARTED;
            case STARTED:
                boolean done = highlightWalls(wall);
                if (done) {
                    selectedWall = wall;
                    turnState = TurnState.WALLSSELECTED;
                }
                break;
        }
    }

    private boolean highlightWalls(Wall wall) {
        if (wall.isBlocked()) {return false;}
        List<Wall> completions = grid.getPossibleWallCompletions(wall);
        if (completions.size() > 0) {
            for (Wall w: completions) {
                w.highlight();
            }
            return true;
        }
        return false;
    }

    enum TurnState {STARTED, NODESSELECTED, WALLSSELECTED}
}
