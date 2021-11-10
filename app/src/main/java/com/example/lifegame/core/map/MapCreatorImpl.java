package com.example.lifegame.core.map;

public class MapCreatorImpl implements MapCreator {

    @Override
    public boolean[][] generateMap(int size, Dot... dots) {
        boolean[][] map = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = false;
            }
        }
        return map;
    }
}
