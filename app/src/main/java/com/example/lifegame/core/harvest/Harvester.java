package com.example.lifegame.core.harvest;

import java.time.Instant;

public interface Harvester {
    boolean[][] getNewEra(boolean[][] map);
    Instant getEraOfLastHarvest();
}
