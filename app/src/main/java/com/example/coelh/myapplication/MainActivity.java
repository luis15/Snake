package com.example.coelh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snakeView = (SnakeView)findViewById(R.id.snakeView);
    }
    @Override
    public void onPause(){
        super.onPause();
        snakeView.stopGame();
    }
}
