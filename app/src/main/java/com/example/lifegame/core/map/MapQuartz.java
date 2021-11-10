package com.example.lifegame.core.map;

public interface MapQuartz {
    boolean isHarvestTime();
    void setMillis(Long millis);
    void run();
    void stop();
}
