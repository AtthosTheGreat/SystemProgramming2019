package com.raulbrumar.threadprogressandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ProgressThread.ProgressThreadCallBack
{
    private static int num = 0;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get textview and set scrolling movement method
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onButtonPressed(View view)
    {
        // start new thread with a unique name
        String threadName = "T" + num;
        num++;

        new ProgressThread(threadName, this).start();
    }

    @Override
    public void progressPercentage(final String name, final int progress)
    {
        // update the UI with the progress
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                textView.append(new Date() + ". Thread: " + name + ". Progress: " + progress + "\n");
            }
        });
    }

    @Override
    public void progressFinished(final String name)
    {
        // update the UI when a thread has finished its tasks
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                textView.append(new Date() + ". Thread: " + name + ". FINISHED\n");
            }
        });
    }
}
