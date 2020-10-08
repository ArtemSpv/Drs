package com.example.drs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(1);

    }

    public void startActivity(int num) {
        switch (num) {
            case 1:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                break;
        }
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return true;
    }
}