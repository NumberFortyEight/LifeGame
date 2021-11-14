package com.example.lifegame;

import com.example.lifegame.core.harvest.HarvesterImpl;
import com.example.lifegame.core.map.MapCreator;
import com.example.lifegame.core.map.MapCreatorImpl;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;

import org.junit.Test;

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

    //@Test
    public void printCreatorMap() {
        boolean[][] map = mapCreator.generateMap(10);
        System.out.println(seer.showAsString(map));
    }

    //@Test
    public void neighborsAlwaysFound() throws InterruptedException {
        boolean[][] map = mapCreator.generateRandomMap(15);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            map = harvester.getNewEra(map);
            System.out.println(seer.showAsString(map));
        }
    }

    @SuppressWarnings("unused")
    private int calculateNeighbors(boolean[][] map) {
        int count = 0;
        for (boolean[] rows : map) {
            for (int y = 0; y < map[0].length; y++) {
                if (rows[y]) ++count;
            }
        }
        return count;
    }

}