package com.example.lifegame.view;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.lifegame.core.MapOverlord;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;

import lombok.SneakyThrows;

public class MapRenderEngine {

    private SurfaceHolder surfaceHolder;
    private final MapOverlord mapOverlord;
    private final Seer seer = new SeerImpl();

    Runnable runnable = new Runnable(){
        @SneakyThrows
        @Override
        public void run() {
            while (!mapOverlord.isDestroyed()){
                if (!mapOverlord.isHarvestTime())
                    continue;

                Canvas canvas;
                if (surfaceHolder == null || (canvas = surfaceHolder.lockCanvas()) == null) {
                    synchronized (MapRenderEngine.this) {
                        MapRenderEngine.this.wait();
                    }
                } else {
                    seer.showOnCanvas(canvas, mapOverlord.getCurrentMap());
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    };

    public MapRenderEngine(SurfaceView map, MapOverlord mapOverlord) {
        this.mapOverlord = mapOverlord;
        Thread thread = new Thread(runnable, "map_thread");
        thread.start();
        SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                surfaceHolder = holder;
                synchronized (MapRenderEngine.this) {
                    MapRenderEngine.this.notifyAll();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                surfaceHolder = holder;
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                surfaceHolder = null;
            }
        };

        map.getHolder().addCallback(callback);
    }
}
