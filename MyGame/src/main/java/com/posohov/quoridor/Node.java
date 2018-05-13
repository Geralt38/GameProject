package com.posohov.quoridor;


public class Node {

    public final int x;
    public final int y;
    private boolean highlighted;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        highlighted = false;
    }

    public void highlight() {highlighted = true;}

    public void dehighlight() {highlighted = false;}

    public boolean isHighlighted() {return highlighted;}
}
