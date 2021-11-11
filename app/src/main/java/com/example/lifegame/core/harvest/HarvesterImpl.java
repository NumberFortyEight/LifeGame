package com.example.lifegame.core.harvest;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HarvesterImpl implements Harvester {

    private Instant lastHarvestEra = Instant.now();
    private boolean isHarvest;

    @Override
    public boolean[][] getNewEra(boolean[][] map) {
        isHarvest = true;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                boolean[] neighbors = findNeighbors(map, x, y);
                int livingCount = findLivingCount(neighbors);
                if (livingCount == 3)
                    map[x][y] = true;
                if (livingCount < 2 || livingCount > 3)
                    map[x][y] = false;
            }
        }
        lastHarvestEra = Instant.now();
        isHarvest = false;
        return map;
    }

    private int findLivingCount(boolean[] neighbors) {
        int counter = 0;
        for (boolean neighbor : neighbors) {
            if (neighbor)
                ++counter;
        }
        return counter;
    }

    public boolean[] findNeighbors(boolean[][] map, final int x, final int y) {
        boolean[] neighbors = new boolean[8];
        neighbors[4] = findLeft(map, x, y);
        neighbors[5] = findRight(map, x, y);
        neighbors[6] = findTop(map, x, y);
        neighbors[7] = findBottom(map, x, y);

        int length = map.length - 1;
        boolean wentTop = x - 1 < 0;
        boolean wentDown = length < x + 1;
        boolean wentLeft = y - 1 < 0;
        boolean wentRight = length < y + 1;

        if (wentTop && wentLeft) {
            neighbors[0] = map[length][length];
            neighbors[1] = findLeft(map, x + 1, y);
            neighbors[2] = findTop(map, x, y + 1);
            neighbors[3] = findRight(map, x + 1, y);
            return neighbors;
        }
        if (wentDown && wentLeft) {
            neighbors[0] = findBottom(map, x, y + 1);
            neighbors[1] = findLeft(map, x - 1, y);
            neighbors[2] = findTop(map, x, y + 1);
            neighbors[3] = map[0][length];
            return neighbors;
        }
        if (wentDown && wentRight) {
            neighbors[1] = findLeft(map, x - 1, y);
            neighbors[2] = findRight(map, x - 1, y);
            neighbors[3] = findBottom(map, x, y - 1);
            neighbors[0] = map[0][0];
            return neighbors;
        }
        if (wentTop && wentRight) {
            neighbors[1] = findTop(map, x, y - 1);
            neighbors[2] = map[length][0];
            neighbors[3] = findBottom(map, x, y - 1);
            neighbors[0] = findRight(map, x + 1, y);
            return neighbors;
        }

        if (wentLeft || wentRight) {
            neighbors[1] = findLeft(map, x - 1, y);
            neighbors[2] = findRight(map, x - 1, y);
            neighbors[3] = findLeft(map, x + 1, y);
            neighbors[0] = findRight(map, x + 1, y);
            return neighbors;
        }

        neighbors[1] = findTop(map, x, y - 1);
        neighbors[2] = findTop(map, x, y + 1);
        neighbors[3] = findBottom(map, x, y - 1);
        neighbors[0] = findBottom(map, x, y + 1);
        return neighbors;
    }

    private boolean findBottom(boolean[][] map, int x, int y) {
        boolean bottom;
        try {
            bottom = map[++x][y];
        } catch (IndexOutOfBoundsException e) {
            bottom = map[0][y];
        }
        return bottom;
    }

    private boolean findTop(boolean[][] map, int x, int y) {
        boolean top;
        try {
            top = map[--x][y];
        } catch (IndexOutOfBoundsException e) {
            top = map[map.length - 1][y];
        }
        return top;
    }

    private boolean findRight(boolean[][] map, int x, int y) {
        boolean right;
        try {
            right = map[x][++y];
        } catch (IndexOutOfBoundsException e) {
            right = map[x][0];
        }
        return right;
    }

    private boolean findLeft(boolean[][] map, int x, int y) {
        boolean left;
        try {
            left = map[x][--y];
        } catch (IndexOutOfBoundsException e) {
            left = map[x][map[0].length - 1];
        }
        return left;
    }

    @Override
    public Instant getEraOfLastHarvest() {
        return isHarvest ? Instant.now() : lastHarvestEra;
    }
}
