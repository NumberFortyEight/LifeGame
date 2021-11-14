package com.example.lifegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lifegame.core.MapOverlord;
import com.example.lifegame.core.MapOverlordImpl;
import com.example.lifegame.core.seer.Seer;
import com.example.lifegame.core.seer.SeerImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private final Seer seer = new SeerImpl();
    private final MapOverlord mapOverlord = new MapOverlordImpl();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private static final int DEFAULT_MAP_SIZE = 25;
    private static final long DEFAULT_REFRESH_RATE = 25L;

    private Button run;
    private Button step;
    private Button stop;
    private EditText hz;
    private TextView map;
    private Button setup;
    private EditText mapSize;
    private Button defaultMap;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = findViewById(R.id.map);
        hz = findViewById(R.id.hz);
        mapSize = findViewById(R.id.mapSize);
        setup = findViewById(R.id.setup);
        run = findViewById(R.id.run);
        stop = findViewById(R.id.stop);
        defaultMap = findViewById(R.id.defaultMap);
        step = findViewById(R.id.step);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hz.setText(String.valueOf(DEFAULT_REFRESH_RATE));
        mapSize.setText(String.valueOf(DEFAULT_MAP_SIZE));

        setup.setOnClickListener(v -> {
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
            renderMap();
        });

        stop.setOnClickListener(v -> {
            executor.shutdown();
            mapOverlord.stop();
        });

        defaultMap.setOnClickListener(v -> {
            mapOverlord.clear();
            renderMap();
        });
        run.setOnClickListener(v -> mapOverlord.run());
        run.setOnClickListener(v -> runMap());
        step.setOnClickListener(v -> renderMap());
    }

    private void renderMap() {
        runOnUiThread(() -> map.setText(getMapAsString()));
    }

    private void runMap(){
        mapOverlord.run();
        if (executor.isShutdown()) executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> map.setText(getMapAsString()), 0, DEFAULT_REFRESH_RATE, TimeUnit.MICROSECONDS);
    }

    private String getMapAsString() {
        return seer.showAsString(mapOverlord.getCurrentMap());
    }
}