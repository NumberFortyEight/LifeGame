package com.example.lifegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifegame.core.MapOverlord;
import com.example.lifegame.core.MapOverlordImpl;

public class MainActivity extends AppCompatActivity {

    //where DI without pain???
    private final MapOverlord audience = new MapOverlordImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}