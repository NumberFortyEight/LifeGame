package com.example.lifegame.core;

import com.example.lifegame.core.map.Dot;
import com.example.lifegame.core.map.MapCreator;
import com.example.lifegame.core.map.MapQuartz;

public interface MapOverlord {
    void generateMap(int size, Dot... dots);
    boolean[][] getCurrentMap();
    void createLife(int x, int y);
    boolean isChanged();
    void setChanged(boolean isChanged);
    void setMillis(Long millis);
    boolean isHarvestTime();
    boolean running();
    void run();
    void stop();
    void destroy();
    boolean isDestroyed();
    void clear();
}
