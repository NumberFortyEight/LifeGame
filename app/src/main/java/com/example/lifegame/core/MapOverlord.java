package com.example.lifegame.core;

import com.example.lifegame.core.map.Dot;
import com.example.lifegame.core.map.MapCreator;
import com.example.lifegame.core.map.MapQuartz;

public interface MapOverlord {
    boolean[][] generateMap(int size, Dot... dots);
    boolean[][] getCurrentMap();
    void setMillis(Long millis);
    void run();
    void stop();
    void clear();
}
