package com.posohov.quoridor;


public class TurnInfo {

    TurnType turnType;
    int x;
    int y;
    boolean isHorizontal;

    enum TurnType {MOVE, WALL}
}
