package com.example.lifegame.core.map;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.lifegame.core.harvest.Harvester;

import java.time.Duration;
import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MapQuartzImpl implements MapQuartz {

    private Duration duration;
    private final Harvester harvester;
    private boolean isTimeStopped;


    public MapQuartzImpl(Harvester harvester) {
        this.harvester = harvester;
    }

    @Override
    public boolean isHarvestTime() {
        if (isTimeStopped)
            return false;
        Instant harvestTime = harvester.getEraOfLastHarvest().plusMillis(duration.toMillis());
        return harvestTime.isBefore(Instant.now());
    }

    @Override
    public void setMillis(Long millis) {
        this.duration = Duration.ofMillis(millis);
    }

    @Override
    public void run() {
        isTimeStopped = false;
    }

    @Override
    public void stop() {
        isTimeStopped = true;
    }
}
