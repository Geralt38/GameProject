package com.posohov.quoridor;


public class Node {

    public final int x;
    public final int y;
    public boolean highlighted;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        highlighted = false;
    }
}
