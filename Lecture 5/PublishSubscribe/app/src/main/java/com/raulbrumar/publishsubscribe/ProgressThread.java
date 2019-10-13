package com.raulbrumar.publishsubscribe;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Date;

public class ProgressThread extends Thread
{
    private Context context;;

    public ProgressThread(Context context)
    {
        this.context = context;
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                sleep(2000);
                finishPart("Chassis");
                sleep(1500);
                finishPart("Engine");
                sleep(1000);
                finishPart("Transmission");
                sleep(1000);
                finishPart("Interior");
                sleep(500);
                finishPart("Windshield");
                sleep(500);
                finishPart("Wheels");
                sleep(100);
                finishPart("Windshield wipers");
                sleep(300);
                finishPart("Air conditioning");
                sleep(1500);
                finishPart("Alternator");
                sleep(2000);
                finishCar();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // updates car progress by sending the latest produced part
    private void finishPart(String partName)
    {
        Intent intent1 = new Intent("car_part");
        intent1.putExtra("part", partName);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
    }

    // notifies that a car has been finished
    private void finishCar()
    {
        Intent intent1 = new Intent("car_done");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
    }
}
