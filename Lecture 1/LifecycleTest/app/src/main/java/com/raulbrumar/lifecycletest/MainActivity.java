package com.raulbrumar.lifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private String lifecycle = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "triggered onCreate", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onCreate.");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Toast.makeText(this, "triggered onStart", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onStart.");
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Toast.makeText(this, "triggered onResume", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onResume.");
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Toast.makeText(this, "triggered onPause", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onPause.");
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Toast.makeText(this, "triggered onStop", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onStop.");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        Toast.makeText(this, "triggered onRestart", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onRestart.");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        Toast.makeText(this, "triggered onDestroy", Toast.LENGTH_SHORT).show();
        Log.println(Log.INFO, lifecycle, "Called onDestroy.");
    }
}
