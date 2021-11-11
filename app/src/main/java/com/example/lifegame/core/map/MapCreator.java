package com.example.lifegame.core.map;

public interface MapCreator {
    boolean[][] generateMap(int size, Dot ... dots);
    boolean[][] generateRandomMap(int size);
}
