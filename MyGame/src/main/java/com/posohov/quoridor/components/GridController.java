package com.posohov.quoridor.components;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.posohov.baseengine.Camera;
import com.posohov.baseengine.Components.GameComponent;
import com.posohov.quoridor.Grid;
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
    private Paint paint;

    private Bitmap nodeBitmap;
    private Bitmap wallConnectorBitmap;
    private Bitmap wallHorizontalBitmap;
    private Bitmap wallVerticalBitmap;
    private Bitmap player1Bitmap;
    private Bitmap player2Bitmap;

    private List<Player> players;
    private Player currentPlayer;

    @Override
    public void start() {
        super.start();
        grid = new Grid();

        paint = object.scene.getPaint();

        Camera camera = object.scene.getCamera();
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


    }

    private void setPlayers() {
        players = new ArrayList<Player>();
        players.add(new HumanPlayer(4, 8, this, grid, player1Bitmap));
        players.add(new AIPlayer(4, 0, this, grid, player2Bitmap));
    }

    private void prepareBitmaps(Camera camera) {
        int screenSquareSide = (int) (camera.gameToScreenLength(squareSide));
        int screenWallWidth = (int) (camera.gameToScreenLength(wallWidth));

        nodeBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.node);
        wallConnectorBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallconnector);
        wallHorizontalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallhorizontal);
        wallVerticalBitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.wallvertical);
        player1Bitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.player1);
        player2Bitmap =  BitmapFactory.decodeResource(object.scene.getResources(), R.drawable.player2);

        nodeBitmap = Bitmap.createScaledBitmap(nodeBitmap, screenSquareSide, screenSquareSide , false);
        wallConnectorBitmap = Bitmap.createScaledBitmap(wallConnectorBitmap, screenWallWidth, screenWallWidth , false);
        wallHorizontalBitmap = Bitmap.createScaledBitmap(wallHorizontalBitmap, screenSquareSide, screenWallWidth , false);
        wallVerticalBitmap = Bitmap.createScaledBitmap(wallVerticalBitmap, screenWallWidth, screenSquareSide , false);
        player1Bitmap = Bitmap.createScaledBitmap(player1Bitmap, screenSquareSide, screenSquareSide , false);
        player2Bitmap = Bitmap.createScaledBitmap(player2Bitmap, screenSquareSide, screenSquareSide , false);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        super.draw(canvas, camera);
        Wall[][] vWalls = grid.getVerticalWalls();
        Wall[][] hWalls = grid.getHorizontalWalls();
        boolean[][] connectors = grid.getWallConnectors();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                camera.drawSprite(canvas, nodeBitmap, x + i * (wallWidth + squareSide), j * (wallWidth + squareSide), paint);
                //Log.d("cords", (x + i * wallWidth) + " " + (j * wallWidth));
                if (i<8) {
                    if (vWalls[i][j].isBlocked()) {
                        camera.drawSprite(canvas, wallVerticalBitmap, x +(i + 1) * squareSide + i * wallWidth, j * ( squareSide + wallWidth), paint);
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
                    }
                }
            }
        }

        for (Player player: players) {
            camera.drawSprite(canvas, player.getSprite(), x + player.getX() * (wallWidth + squareSide), player.getY() * (wallWidth + squareSide), paint);
        }
    }

    @Override
    public void onPlayerTurnEnd(TurnInfo turnInfo) {

    }
}
