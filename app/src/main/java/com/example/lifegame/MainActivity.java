package com.example.lifegame;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifegame.core.MapOverlord;
import com.example.lifegame.core.MapOverlordImpl;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;
import com.example.lifegame.view.MapRenderEngine;

import lombok.SneakyThrows;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private MapRenderEngine mapRenderEngine;
    private final MapOverlord mapOverlord = new MapOverlordImpl();

    private static final int DEFAULT_MAP_SIZE = 25;
    private static final long DEFAULT_REFRESH_RATE = 25L;

    private Button run;
    private Button step;
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
        step = findViewById(R.id.step);
        map = findViewById(R.id.map);
        run = findViewById(R.id.run);
        hz = findViewById(R.id.hz);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapSize.setText(String.valueOf(DEFAULT_MAP_SIZE));
        hz.setText(String.valueOf(DEFAULT_REFRESH_RATE));
        setup.setOnClickListener(v -> applySettings());
        mapOverlord.generateMap(25);
        mapRenderEngine = new MapRenderEngine(map, mapOverlord);
    }

    private void applySettings(){
        Editable refresh = hz.getText();
        if (refresh != null && !refresh.toString().isEmpty()) {
            long refreshRate = Long.parseLong(refresh.toString());
            if (refreshRate > 15 && refreshRate < 200) {
                mapOverlord.setMillis(refreshRate);
            }
        } else mapOverlord.setMillis(DEFAULT_REFRESH_RATE);

        Editable size = mapSize.getText();
        if (size != null && !size.toString().isEmpty()) {
            int mapSize = Integer.parseInt(size.toString());
            if (mapSize > 5 && mapSize < 100) {
                mapOverlord.generateMap(mapSize);
            }
        } else mapOverlord.generateMap(DEFAULT_MAP_SIZE);
    }
}