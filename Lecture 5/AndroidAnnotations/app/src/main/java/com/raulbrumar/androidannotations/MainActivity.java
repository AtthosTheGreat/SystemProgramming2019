package com.raulbrumar.androidannotations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ScrollingView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
{
    // get the textView
    @ViewById(R.id.textView)
    TextView textView;

    // runs on background
    @Background
    void runOnBackground()
    {
        int x = 5;
        try
        {
            while(x != -1)
            {
                runCodeOnUIThread("Running in background: " + x);
                Thread.sleep(500);
                x--;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // runs of UI thread
    @UiThread
    void runCodeOnUIThread(String s)
    {
        textView.append(s + "\n");
    }

    // add onClick listener
    @Click(R.id.button)
    void onClick()
    {
        runOnBackground();
    }
}
