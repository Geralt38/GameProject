package com.posohov.quoridor;


public class Node implements Comparable{

    public final int x;
    public final int y;
    private boolean highlighted;

    Node pathParent;
    public double costFromStart;
    public double estimatedCostToGoal;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        highlighted = false;
    }

    public void highlight() {highlighted = true;}

    public void dehighlight() {highlighted = false;}

    public boolean isHighlighted() {return highlighted;}

    public int compareTo(Object other) {
        double thisValue = this.getCost();
        double otherValue = ((Node)other).getCost();

        double v = thisValue - otherValue;
        return (v>0)?1:(v<0)?-1:0;
    }

    public double getCost() {
        return costFromStart + estimatedCostToGoal;
    }

    public double getCost(Node node) {
        return 1;
    }

    public double getEstimatedCost(Node node) {
        return Math.sqrt(Math.pow((double)x - (double)node.x, 2D) + Math.pow((double)y - (double)node.y, 2D));
    }
}
