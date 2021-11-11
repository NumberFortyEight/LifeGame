package com.example.lifegame.core.seer;

public class SeerImpl implements Seer {
    @Override
    public String showAsString(boolean[][] map) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] booleans : map) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(" ").append(booleans[j] ? "#" : "-").append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
