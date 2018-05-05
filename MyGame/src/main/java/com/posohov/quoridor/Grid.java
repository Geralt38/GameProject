package com.posohov.quoridor;


public class Grid {

    private Node[][] nodes;
    private Wall[][] horizontalWalls;
    private Wall[][] verticalWalls;
    private boolean[][] wallConnectors;
    private Node player1Node;
    private Node player2Node;

    public Grid() {
        nodes = new Node[9][9];
        horizontalWalls = new Wall[9][8];
        verticalWalls = new Wall[8][9];
        wallConnectors = new boolean[8][8];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j] = new Node(i,j);
                if (i<8) {
                    verticalWalls[i][j] = new Wall(i,j);
                    if (j<8) {
                        wallConnectors[i][j] = false;
                    }
                }
                if (j<8) {
                    horizontalWalls[i][j] = new Wall(i,j);
                }
            }
        }
        player1Node = nodes[4][8];
        player2Node = nodes[4][0];
    }

    public Node getPlayer1Node() {
        return player1Node;
    }

    public Node getPlayer2Node() {
        return player2Node;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public Wall[][] getHorizontalWalls() {
        return horizontalWalls;
    }

    public Wall[][] getVerticalWalls() {
        return verticalWalls;
    }

    public boolean[][] getWallConnectors() {
        return wallConnectors;
    }
}
