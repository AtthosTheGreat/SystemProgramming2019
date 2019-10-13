package com.raulbrumar.threadprogressandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity
{
    private static int num = 0;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the textbox and set its movement method
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onButtonPressed(View view)
    {
        // start a new async task which runs at the same time as the other async tasks
        new ProgressAsync("T" + num).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        num++;
    }

    private class ProgressAsync extends AsyncTask<Void, Integer, Void>
    {
        private String name;

        public ProgressAsync(String name)
        {
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            try {
                // do stuff
                publishProgress(0);
                sleep(1000);

                publishProgress(10);
                sleep(1000);

                publishProgress(20);
                sleep(1000);

                publishProgress(30);
                sleep(1000);

                publishProgress(40);
                sleep(1000);

                publishProgress(50);
                sleep(1000);

                publishProgress(60);
                sleep(1000);

                publishProgress(70);
                sleep(1000);

                publishProgress(80);
                sleep(1000);

                publishProgress(90);
                sleep(1000);

                publishProgress(100);
                sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            // update progress on UI thread when called
            Log.d("AAA", "onProgressUpdate: " + name + ": " + values[0]);
            textView.append(new Date() + ": " + name + ". Progress: " + values[0] + "\n");
        }
    }
}
