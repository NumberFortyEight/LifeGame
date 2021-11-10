package com.example.lifegame;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.lifegame.core.map.Dot;
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
        boolean[][] map = mapCreator.generateMap(8);
        System.out.println(seer.showAsString(map));
    }

}