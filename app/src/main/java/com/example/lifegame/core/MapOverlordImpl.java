package com.example.lifegame.core;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.lifegame.core.harvest.Harvester;
import com.example.lifegame.core.harvest.HarvesterImpl;
import com.example.lifegame.core.map.Dot;
import com.example.lifegame.core.map.MapCreator;
import com.example.lifegame.core.map.MapCreatorImpl;
import com.example.lifegame.core.map.MapQuartz;
import com.example.lifegame.core.map.MapQuartzImpl;

public class MapOverlordImpl implements MapOverlord {

    private boolean[][] map;

    private final MapCreator mapCreator;
    private final MapQuartz mapQuartz;
    private final Harvester harvester;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MapOverlordImpl() {
        mapCreator = new MapCreatorImpl();
        harvester = new HarvesterImpl();
        mapQuartz = new MapQuartzImpl(harvester);
    }

    @Override
    public boolean[][] getCurrentMap() {
        if (!mapQuartz.isHarvestTime())
            return map;
        map = harvester.getNewEra(map);
        return map;
    }

    @Override
    public void run() {
        mapQuartz.run();
    }

    @Override
    public void stop() {
        mapQuartz.stop();
    }

    @Override
    public void clear() {
        map = null;
    }

    @Override
    public void setMillis(Long millis) {
        mapQuartz.setMillis(millis);
    }

    @Override
    public boolean[][] generateMap(int size, Dot... dots) {
        map = mapCreator.generateMap(size, dots);
        return map;
    }
}
