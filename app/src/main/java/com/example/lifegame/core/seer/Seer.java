package com.example.lifegame.core.seer;

import android.graphics.Canvas;

public interface Seer {
    String showAsString(boolean[][] map);
    void showOnCanvas(Canvas canvas, boolean[][] map);
}
