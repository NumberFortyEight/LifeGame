package com.example.lifegame.core.seer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

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
        final int deviation = canvas.getWidth() / map.length - 1;
        paint.setStrokeWidth(deviation);
        long deviationY = deviation;
        for (int i = 0; i < map.length; i++) {
            long deviationX = deviation;
            for (int j = 0; j < map[0].length; j++) {
                if (!map[i][j]) {
                    paint.setColor(Color.BLACK);
                }
                long x = deviationX;
                deviationX += deviation;
                canvas.drawPoint(x, deviationY, paint);
                paint.setColor(Color.WHITE);
            }
            deviationY += deviation;
        }
    }
}
