package com.example.lifegame.core.seer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class SeerImpl implements Seer {

    @Override
    public String showAsString(boolean[][] map) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] booleans : map) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(" ").append(booleans[j] ? "*" : "-").append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void showOnCanvas(Canvas canvas, boolean[][] map) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        long deviationY = 0;
        for (int i = 0; i < map.length; i++) {
            long deviationX = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (!map[i][j]) {
                    paint.setColor(Color.BLACK);
                }
                long x = deviationX;
                long y = deviationY;
                deviationX += 20;
                canvas.drawPoint(x, y, paint);
                paint.setColor(Color.WHITE);
            }
            deviationY +=20;
        }
    }
}
