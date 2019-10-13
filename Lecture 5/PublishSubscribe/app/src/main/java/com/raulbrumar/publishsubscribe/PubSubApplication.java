package com.raulbrumar.publishsubscribe;

import android.app.Application;

public class PubSubApplication extends Application
{
    ProgressThread thread;

    @Override
    public void onCreate()
    {
        super.onCreate();

        // start the thread for creating cars independent of activities
        thread = new ProgressThread(this);
        thread.start();
    }
}
