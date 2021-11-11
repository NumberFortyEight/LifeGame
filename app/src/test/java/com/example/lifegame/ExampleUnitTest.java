package com.example.lifegame;

import org.junit.Assert;
import org.junit.Test;

import com.example.lifegame.core.harvest.HarvesterImpl;
import com.example.lifegame.core.map.MapCreator;
import com.example.lifegame.core.map.MapCreatorImpl;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private final Seer seer = new SeerImpl();
    private final MapCreator mapCreator = new MapCreatorImpl();
    private final HarvesterImpl harvester = new HarvesterImpl();

    @Test
    public void printTest() {
        boolean[][] map = {
                {true,false,true,true,false,false},
                {false,true,false,true,false,true},
                {true,false,false,false,false,false},
                {false,false,false,false,false,false},
                {false,false,false,true,false,false},
                {false,true,false,false,false,true}
        };
        System.out.println(seer.showAsString(map));
    }

    @Test
    public void printCreatorMap() {
        boolean[][] map = mapCreator.generateMap(10);
        System.out.println(seer.showAsString(map));
    }

    @Test
    public void neighborsAlwaysFound() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            boolean[][] map = mapCreator.generateMap(10);
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            map[x][y] = true;
            harvester.findNeighbors(map, x, y);
            System.out.println(seer.showAsString(map));
            int neighbors = calculateNeighbors(map);
            Assert.assertEquals(neighbors, 9);
        }
    }

    private int calculateNeighbors(boolean[][] map) {
        int count = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y]) ++count;
            }
        }
        return count;
    }

}