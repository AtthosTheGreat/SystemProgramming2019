package com.raulbrumar.fetchdatafrominternetandroid;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkThread extends Thread
{
    public interface NetworkInterfaceCallback
    {
        public void sendResults(ArrayList<String> result);
    }

    private ArrayList<String> data = new ArrayList<String>();

    private NetworkInterfaceCallback callback;

    private String givenURL;

    // setup network thread
    public NetworkThread(NetworkInterfaceCallback callback, String givenURL)
    {
        this.callback = callback;
        this.givenURL = givenURL;
    }

    @Override
    public void run()
    {
        try
        {
            // start the connection and read data
            URL url = new URL(givenURL);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            // httpConnection.connect(); -> can be skipped as it will be done automatically

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

            String line = reader.readLine();
            while(line != null)
            {
                // read line by line and put inside big String
                // Log.d("AAA", line);
                data.add(line);
                line = reader.readLine();

                // System.out.println(line);
            }

            reader.close();
            callback.sendResults(data); // callback with the results
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
