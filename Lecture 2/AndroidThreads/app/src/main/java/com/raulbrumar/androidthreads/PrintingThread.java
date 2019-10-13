package com.raulbrumar.androidthreads;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PrintingThread extends Thread
{
    private TextView textView;

    public PrintingThread(TextView t)
    {
        textView = t;
    }

    // runs every 500ms and prints
    public void run()
    {
        try
        {
            while(true)
            {
                // using post() method to update the UI on the UI thread
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(Calendar.getInstance().getTime() + " Tiisu, We want more!!!\n");
                    }
                });
                sleep(500);
            }
        }
        catch(Exception e)
        {

        }
    }
}
