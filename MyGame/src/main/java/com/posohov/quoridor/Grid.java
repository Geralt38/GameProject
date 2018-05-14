package com.posohov.quoridor;


import android.util.Log;

import com.posohov.quoridor.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Grid{

    private Node[][] nodes;
    private Wall[][] horizontalWalls;
    private Wall[][] verticalWalls;
    private boolean[][] wallConnectors;
    List<Player> players;

    public Grid() {
        players = new ArrayList<Player>();
        nodes = new Node[9][9];
        horizontalWalls = new Wall[9][8];
        verticalWalls = new Wall[8][9];
        wallConnectors = new boolean[8][8];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j] = new Node(i,j);
                if (i<8) {
                    verticalWalls[i][j] = new Wall(i,j, false);
                    if (j<8) {
                        wallConnectors[i][j] = false;
                    }
                }
                if (j<8) {
                    horizontalWalls[i][j] = new Wall(i,j, true);
                }
            }
        }
    }

    public Grid(Grid grid) {
        players = new ArrayList<Player>();
        for (Player player : grid.players) {
            addPlayer(player);
        }
        nodes = new Node[9][9];
        horizontalWalls = new Wall[9][8];
        verticalWalls = new Wall[8][9];
        wallConnectors = new boolean[8][8];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j] = new Node(i,j);
                if (i<8) {
                    verticalWalls[i][j] = new Wall(i,j, grid.getVerticalWalls()[i][j].isBlocked(),false);
                    if (j<8) {
                        wallConnectors[i][j] = false;
                    }
                }
                if (j<8) {
                    horizontalWalls[i][j] = new Wall(i,j, grid.getHorizontalWalls()[i][j].isBlocked(), true);
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void dehighlightEverything() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j].dehighlight();
                if (i<8) {
                    verticalWalls[i][j].dehighlight();
                }
                if (j<8) {
                    horizontalWalls[i][j].dehighlight();
                }
            }
        }
    }

    public void dehighlightNodes() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nodes[i][j].dehighlight();
            }
        }
    }

    public void blockWalls(Wall wall1, Wall wall2) {
        wall1.block();
        wall2.block();
        if (wall1.isHorizontal()) {
            int x = Math.min(wall1.x, wall2.x);
            wallConnectors[x][wall1.y] = true;
        } else {
            int y = Math.min(wall1.y, wall2.y);
            wallConnectors[wall1.x][y] = true;
        }
    }

    public void blockWall(int x, int y, boolean isHorisontal) {
        if (isHorisontal) {
            horizontalWalls[x][y].block();
        } else {
            verticalWalls[x][y].block();
        }
    }

    public List<Node> getPossibleMoveLocations(int x, int y) {
        Node node = nodes[x][y];
        List<Node> locations = new ArrayList<Node>();
        if ((x > 0) && canMove(node,nodes[x - 1][y])) {
            if (playerOnNode(nodes[x-1][y])) {
                if ((x - 1 > 0) && canMove(nodes[x - 1][y], nodes[x - 2][y])) {
                    locations.add(nodes[x-2][y]);
                } else {
                    locations.addAll(getSideNodes(x - 1, y, false));
                }
            } else {
                locations.add(nodes[x - 1][y]);
            }
        }
        if ((x < nodes.length-1) && canMove(node,nodes[x + 1][y])) {
            if (playerOnNode(nodes[x+1][y])) {
                if ((x + 1 < nodes.length - 1) && canMove(nodes[x + 1][y], nodes[x + 2][y])) {
                    locations.add(nodes[x + 2][y]);
                } else {
                    locations.addAll(getSideNodes(x + 1, y, false));
                }
            } else {
                locations.add(nodes[x + 1][y]);
            }
        }
        if ((y > 0) && canMove(node,nodes[x][y - 1]))  {
            if (playerOnNode(nodes[x][y - 1])) {
                if ((y - 1 > 0) && canMove(nodes[x][y - 1], nodes[x][y - 2])) {
                    locations.add(nodes[x][y - 2]);
                } else {
                    locations.addAll(getSideNodes(x, y - 1, true));
                }
            } else {
                locations.add(nodes[x][y - 1]);
            }
        }
        if ((y < nodes.length-1) && canMove(node,nodes[x][y + 1])) {
            if (playerOnNode(nodes[x][y + 1])) {
                if ((y + 1 < nodes.length - 1) && canMove(nodes[x][y + 1], nodes[x][y + 2])) {
                    locations.add(nodes[x][y + 2]);
                } else {
                    locations.addAll(getSideNodes(x, y + 1, true));
                }
            } else {
                locations.add(nodes[x][y + 1]);
            }
        }
        return locations;
    }

    public List<Node> getSideNodes(int x, int y, boolean horizontal) {
        List<Node> sideNodes = new ArrayList<Node>();
        Node node = nodes[x][y];
        if (horizontal) {
            if ((x > 0) && canMove(node, nodes[x-1][y])) {
                sideNodes.add(nodes[x-1][y]);
            }
            if ((x < nodes.length-1) && canMove(node,nodes[x + 1][y])) {
                sideNodes.add(nodes[x + 1][ y]);
            }
        } else {
            if ((y > 0) && canMove(node,nodes[x][y - 1]))  {
                sideNodes.add(nodes[x][ y - 1]);
            }
            if ((y < nodes.length-1) && canMove(node,nodes[x][y + 1])) {
                sideNodes.add(nodes[x][ y + 1]);
            }
        }
        return sideNodes;
    }

    public boolean canMove(Node node1, Node node2) {
        if (node1.y == node2.y) {
            return !verticalWalls[Math.min(node1.x, node2.x)][node1.y].isBlocked();
        } else {
            return !horizontalWalls[node1.x][ Math.min(node1.y, node2.y)].isBlocked();
        }
    }

    public boolean playerOnNode(Node node) {
        for (Player player : players) {
            if ((player.getX() == node.x) && (player.getY() == node.y)) {
                return true;
            }
        }
        return false;
    }

    public List<Wall> getPossibleWallCompletions(Wall wall) {
        List<Wall> walls = new ArrayList<Wall>();
        if (wall.isHorizontal()) {
            if ((wall.x > 0) && !(horizontalWalls[wall.x - 1][wall.y].isBlocked()) && !(blocksPath(wall, horizontalWalls[wall.x - 1][wall.y])) &&
                    !wallConnectors[wall.x-1][wall.y]) {
                walls.add(horizontalWalls[wall.x - 1][wall.y]);
            }
            if ((wall.x < horizontalWalls.length - 1) && !(horizontalWalls[wall.x + 1][wall.y].isBlocked()) && !(blocksPath(wall, horizontalWalls[wall.x + 1][wall.y])) &&
                    !wallConnectors[wall.x][wall.y]) {
                walls.add(horizontalWalls[wall.x + 1][wall.y]);
            }
        } else {
            if ((wall.y > 0) && !(verticalWalls[wall.x][wall.y - 1].isBlocked()) && !(blocksPath(wall, verticalWalls[wall.x][wall.y - 1])) &&
                    !wallConnectors[wall.x][wall.y-1]) {
                walls.add(verticalWalls[wall.x][wall.y - 1]);
            }
            if ((wall.y < verticalWalls[0].length - 1) && !(verticalWalls[wall.x][wall.y + 1].isBlocked()) && !(blocksPath(wall, verticalWalls[wall.x][wall.y + 1])) &&
                    !wallConnectors[wall.x][wall.y]) {
                walls.add(verticalWalls[wall.x][wall.y + 1]);
            }
        }
        return walls;
    }

    public boolean blocksPath(Wall wall1, Wall wall2) {
        Grid newGrid = new Grid(this);
        newGrid.blockWall(wall1.x,wall1.y, wall1.isHorizontal());
        newGrid.blockWall(wall2.x,wall2.y, wall2.isHorizontal());
        for (Player player : players) {
            int row = player.getStartingRow() == 0? 8 : 0;
            boolean pathExists = false;
            for (int i = 0; i < 9; i++) {
                List<Node> path = PathFinder.findPath(newGrid, newGrid.getNodes()[player.getX()][player.getY()], newGrid.getNodes()[i][row]);
                if (path != null) {
                    pathExists = true;
                    break;
                }
            }
            if (!pathExists) {
                return true;
            }
        }
        return false;
    }

    public Wall getWall(int x, int y, boolean isHorizontal) {
        if (isHorizontal) {
            return horizontalWalls[x][y];
        } else {
            return verticalWalls[x][y];
        }
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
