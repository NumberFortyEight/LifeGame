package com.example.lifegame.core.map;

import java.util.Random;

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

    @Override
    public boolean[][] generateRandomMap(int size) {
        boolean[][] map = new boolean[size][size];
        Random random = new Random();
        for (int i = 0; i < size * 2.4; i++) {
            map[random.nextInt(size)][random.nextInt(size)] = true;
        }
        return map;
    }
}
