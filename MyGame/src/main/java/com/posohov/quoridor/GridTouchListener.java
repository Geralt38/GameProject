package com.posohov.quoridor;

public interface GridTouchListener {

    void nodeTouched(int x, int y);

    void wallTouched(int x, int y, boolean isHorizontal);
}
