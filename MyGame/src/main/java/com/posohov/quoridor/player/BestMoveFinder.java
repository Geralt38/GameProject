package com.posohov.quoridor.player;


import android.util.Log;

import com.posohov.quoridor.Grid;
import com.posohov.quoridor.Node;
import com.posohov.quoridor.PathFinder;
import com.posohov.quoridor.PlayerTurnListener;
import com.posohov.quoridor.Wall;

import java.util.ArrayList;
import java.util.List;

public class BestMoveFinder implements Runnable {


    private Grid grid;
    private Grid oldGrid;
    private int playerIndex;
    private PlayerTurnListener callback;

    public BestMoveFinder(Grid grid, int playerIndex, PlayerTurnListener callback) {
        this.grid = new Grid(grid);
        this.oldGrid = grid;
        this.playerIndex = playerIndex;
        this.callback = callback;
    }

    @Override
    public void run() {

        Move move = findBestMove();
        oldGrid.doMove(move, playerIndex);

        callback.onPlayerTurnEnd();
    }

    private Move findBestMove() {

        float score = 200;
        Move bestMove = null;
        List<Move> moves = getMoves(grid.getPlayers().get(playerIndex));
        if (grid.getPlayers().get(playerIndex).wallNumber > 0) {
            moves.addAll(getWallPlacements());
        }

        for (Move move : moves) {

            grid.doMove(move, playerIndex);
            float eval = Evaluate();
            move.value = eval;
            grid.undoMove(move, playerIndex);

            if (eval < score) {
                score = eval;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private List<Move> getMoves(Player player) {

        List<Move> moves = new ArrayList<Move>();
        for (Node node : grid.getPossibleMoveLocations(player.x, player.y)) {
            moves.add(new Move(node.x, node.y, player.x, player.y));
        }
        return moves;
    }

    private List<Move> getWallPlacements() {
        List<Move> moves = new ArrayList<Move>();
        Wall[][] hWalls = grid.getHorizontalWalls();
        Wall[][] vWalls = grid.getVerticalWalls();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ( (grid.canPlaceWall(hWalls[i][j],hWalls[i+1][j])) &&   (!grid.blocksPath(hWalls[i][j], hWalls[i+1][j]))) {
                    moves.add(new Move(i, j, true));
                }
                if ( (grid.canPlaceWall(vWalls[i][j],vWalls[i][j+1])) &&   (!grid.blocksPath(vWalls[i][j], vWalls[i][j+1]))) {
                    moves.add(new Move(i, j, false));
                }
            }
        }
        return moves;
    }

    private float Evaluate() {
        int shortestPath = 100;

        Player player = grid.getPlayers().get(playerIndex);
        float score = -((float)player.getWallNumber())/5;

        int row = player.getStartingRow() == 0? 8 : 0;
        if (row == player.getY()) {return -100f;}
        for (int i = 0; i < 9; i++) {
            List<Node> path = PathFinder.findPath(grid, grid.getNodes()[player.getX()][player.getY()], grid.getNodes()[i][row]);
            if (path != null) {
                if (path.size() < shortestPath) {
                    shortestPath = path.size();
                }
            }
        }
        int shortestEnemyPath = 100;

        player = grid.getPlayers().get((playerIndex == 0? 1 : 0));
        row = player.getStartingRow() == 0? 8 : 0;
        if (row == player.getY()) {return 100f;}
        for (int i = 0; i < 9; i++) {
            List<Node> path = PathFinder.findPath(grid, grid.getNodes()[player.getX()][player.getY()], grid.getNodes()[i][row]);
            if (path != null) {
                if (path.size() < shortestEnemyPath) {
                    shortestEnemyPath = path.size();
                }
            }
        }
        score = score + shortestPath - shortestEnemyPath;
        return score;
    }

}
