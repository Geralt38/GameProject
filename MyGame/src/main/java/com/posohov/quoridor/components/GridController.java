package com.posohov.quoridor.components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;
import com.posohov.baseengine.Scene;
import com.posohov.quoridor.Grid;
import com.posohov.quoridor.GridTouchListener;
import com.posohov.quoridor.Node;
import com.posohov.quoridor.PlayerTurnListener;
import com.posohov.quoridor.TurnInfo;
import com.posohov.quoridor.Wall;
import com.posohov.quoridor.player.AIPlayer;
import com.posohov.quoridor.player.HumanPlayer;
import com.posohov.quoridor.player.Player;

import java.util.ArrayList;
import java.util.List;

import course.labs.graphicslab.R;


public class GridController extends GameComponent implements PlayerTurnListener{

    private Grid grid;
    private float x;
    private float squareSide;
    private float wallWidth;
    private float cameraWidth;
    private float gridSide;
    private Paint paint;

    private Bitmap nodeBitmap;
    private Bitmap wallConnectorBitmap;
    private Bitmap wallHorizontalBitmap;
    private Bitmap wallVerticalBitmap;
    private Bitmap player1Bitmap;
    private Bitmap player2Bitmap;
    private Bitmap highlightedNodeBitmap;
    private Bitmap highlightedWallHorizontalBitmap;
    private Bitmap highlightedWallVerticalBitmap;

    private List<Player> players;
    private Player currentPlayer;

    private Scene.TouchInfo touchInfo;

    @Override
    public void start() {
        super.start();

        touchInfo = object.scene.getTouchInfo();

        grid = new Grid();

        paint = object.scene.getPaint();

        Camera camera = object.scene.getCamera();
        gridSide = camera.getHeight();
        x = (camera.getWidth() - camera.getHeight())/2f;
        squareSide = camera.getHeight()/11f;
        wallWidth = squareSide/4f;

        prepareBitmaps(camera);

        setPlayers();

        currentPlayer = players.get(0);
        currentPlayer.startTurn();
    }

    @Override
    public void update() {
        super.update();
        if (touchInfo.newTouch) {
            if (currentPlayer instanceof GridTouchListener) {
                float tx = touchInfo.x;
                float ty = touchInfo.y;
                if ((tx > x) && (tx < x + gridSide)) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if ((tx > (x + i * (wallWidth + squareSide))) && (tx < (x + i * (wallWidth + squareSide) + squareSide)) &&
                                    (ty > (j * (wallWidth + squareSide))) && (ty < (j * (wallWidth + squareSide) + squareSide))) {
                                ((GridTouchListener) currentPlayer).nodeTouched(i, j);
                            }
                            if (i < 8) {
                                if ((tx > (x +(i + 1) * squareSide + i * wallWidth)) && (tx < (x +(i + 1) * (squareSide + wallWidth))) &&
                                        (ty > (j * ( squareSide + wallWidth))) && (ty < (j * ( squareSide + wallWidth) + squareSide))) {
                                    ((GridTouchListener)currentPlayer).wallTouched(i,j,false);
                                }
                            }
                            if (j < 8) {
                                if ((tx > (x + i * (squareSide + wallWidth))) && (tx < (x + i * (squareSide + wallWidth) + squareSide)) &&
                                        (ty > ((j+1) * squareSide + j * wallWidth)) && (ty < ((j+1) * (squareSide + wallWidth)))) {
                                    ((GridTouchListener)currentPlayer).wallTouched(i,j,true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setPlayers() {
        players = new ArrayList<Player>();
        addPlayer(new HumanPlayer(4, 8, this, grid, player1Bitmap));
        addPlayer(new AIPlayer(4, 0, this, grid, player2Bitmap));
    }

    private void addPlayer(Player player) {
        players.add(player);
        grid.addPlayer(player);
    }

    private void prepareBitmaps(Camera camera) {
        int screenSquareSide = (int) (camera.gameToScreenLength(squareSide));
        int screenWallWidth = (int) (camera.gameToScreenLength(wallWidth));

        nodeBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.node);
        highlightedNodeBitmap = BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.highlightednode);
        wallConnectorBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallconnector);
        wallHorizontalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallhorizontal);
        wallVerticalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallvertical);
        highlightedWallHorizontalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.highlightedwallhorizontal);
        highlightedWallVerticalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.highlightedwallvertical);
        player1Bitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.player1);
        player2Bitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.player2);

        nodeBitmap = Bitmap.createScaledBitmap(nodeBitmap, screenSquareSide, screenSquareSide , false);
        highlightedNodeBitmap = Bitmap.createScaledBitmap(highlightedNodeBitmap, screenSquareSide, screenSquareSide , false);
        wallConnectorBitmap = Bitmap.createScaledBitmap(wallConnectorBitmap, screenWallWidth, screenWallWidth , false);
        wallHorizontalBitmap = Bitmap.createScaledBitmap(wallHorizontalBitmap, screenSquareSide, screenWallWidth , false);
        wallVerticalBitmap = Bitmap.createScaledBitmap(wallVerticalBitmap, screenWallWidth, screenSquareSide , false);
        highlightedWallHorizontalBitmap = Bitmap.createScaledBitmap(highlightedWallHorizontalBitmap, screenSquareSide, screenWallWidth , false);
        highlightedWallVerticalBitmap = Bitmap.createScaledBitmap(highlightedWallVerticalBitmap, screenWallWidth, screenSquareSide , false);
        player1Bitmap = Bitmap.createScaledBitmap(player1Bitmap, screenSquareSide, screenSquareSide , false);
        player2Bitmap = Bitmap.createScaledBitmap(player2Bitmap, screenSquareSide, screenSquareSide , false);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        super.draw(canvas, camera);
        Node[][] nodes = grid.getNodes();
        Wall[][] vWalls = grid.getVerticalWalls();
        Wall[][] hWalls = grid.getHorizontalWalls();
        boolean[][] connectors = grid.getWallConnectors();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nodes[i][j].isHighlighted()) {
                    camera.drawSprite(canvas, highlightedNodeBitmap, x + i * (wallWidth + squareSide), j * (wallWidth + squareSide), paint);
                } else {
                    camera.drawSprite(canvas, nodeBitmap, x + i * (wallWidth + squareSide), j * (wallWidth + squareSide), paint);
                }
                //Log.d("cords", (x + i * wallWidth) + " " + (j * wallWidth));
                if (i<8) {
                    if (vWalls[i][j].isBlocked()) {
                        camera.drawSprite(canvas, wallVerticalBitmap, x +(i + 1) * squareSide + i * wallWidth, j * ( squareSide + wallWidth), paint);
                    } else if (vWalls[i][j].isHighlighted()) {
                        camera.drawSprite(canvas, highlightedWallVerticalBitmap, x +(i + 1) * squareSide + i * wallWidth, j * ( squareSide + wallWidth), paint);
                    }
                    if (j<8) {
                        if (connectors[i][j]) {
                            camera.drawSprite(canvas, wallConnectorBitmap, x +(i + 1) * squareSide + i * wallWidth, (j+1) * squareSide + j * wallWidth, paint);
                        }
                    }
                }
                if (j<8) {
                    if (hWalls[i][j].isBlocked()) {
                        camera.drawSprite(canvas, wallHorizontalBitmap, x + i * (squareSide + wallWidth), (j+1) * squareSide + j * wallWidth, paint);
                    } else if (hWalls[i][j].isHighlighted()) {
                        camera.drawSprite(canvas, highlightedWallHorizontalBitmap, x + i * (squareSide + wallWidth), (j+1) * squareSide + j * wallWidth, paint);
                    }
                }
            }
        }

        for (Player player: players) {
            camera.drawSprite(canvas, player.getSprite(), x + player.getX() * (wallWidth + squareSide), player.getY() * (wallWidth + squareSide), paint);
        }
    }

    @Override
    public void onPlayerTurnEnd() {

    }
}
