package com.raulbrumar.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    public void buttonClick(View view)
    {
        // restart game
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
