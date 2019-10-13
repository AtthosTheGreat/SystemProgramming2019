package com.raulbrumar.androidthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private PrintingThread t = new PrintingThread(null);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startButtonPressed(View view)
    {
        // if thread is not alive, start a new thread, else do nothing
        if (!t.isAlive())
        {
            t = new PrintingThread((TextView) findViewById(R.id.textView));
            t.start();
        }
    }

    public void stopButtonPressed(View view)
    {
        // if thread is alive, kill it, else do nothing
        if (t.isAlive())
        {
            t.interrupt();
        }
    }
}