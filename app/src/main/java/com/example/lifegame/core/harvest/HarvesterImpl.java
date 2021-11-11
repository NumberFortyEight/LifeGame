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
        lastHarvestEra = Instant.now();
        isHarvest = true;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                boolean[] neighbors = findNeighbors(map, x, y);
                int livingCount = findLivingCount(neighbors);

            }
        }
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

    //remove public
    public boolean[] findNeighbors(boolean[][] map, int x, int y) {
        boolean[] neighbors = new boolean[8];
        neighbors[0] = findLeft(map, x, y);
        neighbors[1] = findRight(map, x, y);
        neighbors[2] = findTop(map, x, y);
        neighbors[3] = findBottom(map, x, y);

        findCorners(map, x, y);
        return neighbors;
    }

    private boolean[] findCorners(boolean[][] map, final int x, final int y) {
        int length = map.length - 1;
        boolean wentTop = x - 1 < 0;
        boolean wentDown = length < x + 1;
        boolean wentLeft = y - 1 < 0;
        boolean wentRight = length < y + 1;

        boolean[] corners = new boolean[4];
        if (wentTop && wentLeft) {
            map[length][length] = true;
            corners[0] = map[length][length];
            corners[1] = findLeft(map, x + 1, y);
            corners[2] = findTop(map, x, y + 1);
            corners[3] = findRight(map, x + 1, y);
            return corners;
        }
        if (wentDown && wentLeft){
            map[0][length] = true;
            corners[0] = findBottom(map, x, y + 1);
            boolean topLeft = findLeft(map, x - 1, y);
            boolean bottomLeft = map[0][length];
            boolean topRight = findTop(map, x, y + 1);
            return corners;
        }
        if (wentDown && wentRight) {
            map[0][0] = true;
            boolean topLeft = findLeft(map, x - 1, y);
            boolean topRight = findRight(map, x - 1, y);
            boolean bottomLeft = findBottom(map, x, y - 1);
            corners[0] = map[0][0];
            return corners;
        }
        if (wentTop && wentRight) {
            map[length][0] = true;
            boolean topLeft = findTop(map, x, y - 1);
            boolean topRight = map[length][0];
            boolean bottomLeft = findBottom(map, x, y - 1);
            corners[0] = findRight(map, x + 1, y);
            return corners;
        }

        if (wentLeft || wentRight){
            boolean topLeft = findLeft(map, x - 1, y);
            boolean topRight = findRight(map, x - 1, y);
            boolean bottomLeft = findLeft(map, x + 1, y);
            corners[0] = findRight(map, x + 1, y);
            return corners;
        }

        if (wentTop || wentDown) {
            boolean topLeft = findTop(map, x, y - 1);
            boolean topRight = findTop(map, x, y + 1);
            boolean bottomLeft = findBottom(map, x, y - 1);
            corners[0] = findBottom(map, x, y + 1);
            return corners;
        }
        return corners;
    }

    private boolean findBottom(boolean[][] map, int x, int y) {
        boolean bottom;
        try {
            bottom = map[++x][y];
            map[x][y] = true;
        } catch (IndexOutOfBoundsException e){
            bottom = map[0][y];
            map[0][y] = true;
        }
        return bottom;
    }

    private boolean findTop(boolean[][] map, int x, int y) {
        boolean top;
        try {
            top = map[--x][y];
            map[x][y] = true;
        } catch (IndexOutOfBoundsException e){
            top = map[map.length - 1][y];
            map[map.length - 1][y] = true;
        }
        return top;
    }

    private boolean findRight(boolean[][] map, int x, int y) {
        boolean right;
        try {
            right = map[x][++y];
            map[x][y] = true;
        } catch (IndexOutOfBoundsException e){
            right = map[x][0];
            map[x][0] = true;
        }
        return right;
    }

    private boolean findLeft(boolean[][] map, int x, int y) {
        boolean left;
        try {
            left = map[x][--y];
            map[x][y] = true;
        } catch (IndexOutOfBoundsException e){
            left = map[x][map[0].length - 1];
            map[x][map[0].length - 1] = true;
        }
        return left;
    }

    @Override
    public Instant getEraOfLastHarvest() {
        return isHarvest ? Instant.now() : lastHarvestEra;
    }
}
