package com.example.lifegame.core.harvest;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HarvesterImpl implements Harvester {

    private Instant lastHarvestEra = Instant.now();

    @Override
    public boolean[][] getNewEra(boolean[][] map) {
        lastHarvestEra = Instant.now();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                
            }
        }
        return map;
    }

    @Override
    public Instant getEraOfLastHarvest() {
        return lastHarvestEra;
    }
}
