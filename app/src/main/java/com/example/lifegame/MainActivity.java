package com.example.lifegame;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifegame.core.MapOverlord;
import com.example.lifegame.core.MapOverlordImpl;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;
import com.example.lifegame.view.MapRenderEngine;

import org.apache.commons.lang3.StringUtils;

import lombok.SneakyThrows;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private final MapOverlord mapOverlord = new MapOverlordImpl();

    private static final int DEFAULT_MAP_SIZE = 25;
    private static final int MAX_MAP_SIZE = 100;
    private static final int MIN_MAP_SIZE = 10;

    private static final long DEFAULT_REFRESH_RATE = 25L;
    private static final long MAX_REFRESH_RATE = 400L;
    private static final long MIN_REFRESH_RATE = 25L;

    private Button run;
    private Button stop;
    private EditText hz;
    private Button setup;
    private SurfaceView map;
    private EditText mapSize;
    private Button defaultMap;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultMap = findViewById(R.id.defaultMap);
        mapSize = findViewById(R.id.mapSize);
        setup = findViewById(R.id.setup);
        stop = findViewById(R.id.stop);
        map = findViewById(R.id.map);
        run = findViewById(R.id.run);
        hz = findViewById(R.id.hz);

        mapSize.setText(String.valueOf(DEFAULT_MAP_SIZE));
        hz.setText(String.valueOf(DEFAULT_REFRESH_RATE));
        setup.setOnClickListener(view -> applySettings());
        mapOverlord.generateMap(25);
        mapOverlord.stop();
        new MapRenderEngine(map, mapOverlord);

        defaultMap.setOnClickListener(view -> mapOverlord.generateMap(DEFAULT_MAP_SIZE));
        stop.setOnClickListener(view -> mapOverlord.stop());
        run.setOnClickListener(view -> mapOverlord.run());
    }

    private void applySettings(){
        String refresh = checkSettings(hz.getText());
        String size = checkSettings(mapSize.getText());

        long refreshRate = Long.parseLong(refresh);
        if (refreshRate >= MIN_REFRESH_RATE && refreshRate <= MAX_REFRESH_RATE) {
            mapOverlord.setMillis(refreshRate);
        } else {
            Toast.makeText(MainActivity.this, "Refresh data could be in (15 - 200)", Toast.LENGTH_SHORT).show();
            mapOverlord.setMillis(DEFAULT_REFRESH_RATE);
        }

        int mapSize = Integer.parseInt(size);
        if (mapSize >= MIN_MAP_SIZE && mapSize <= MAX_MAP_SIZE) {
            mapOverlord.generateMap(mapSize);
        } else {
            Toast.makeText(MainActivity.this, "Map size could be in (5 - 100)", Toast.LENGTH_SHORT).show();
            mapOverlord.generateMap(DEFAULT_MAP_SIZE);
        }
    }

    private String checkSettings(CharSequence sequence){
        if (StringUtils.isEmpty(sequence)) {
            Toast.makeText(MainActivity.this, "Fill in the fields", Toast.LENGTH_SHORT).show();
            throw new IllegalStateException();
        }
        return sequence.toString();
    }
}