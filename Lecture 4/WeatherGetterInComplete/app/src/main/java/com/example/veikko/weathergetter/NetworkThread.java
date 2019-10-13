package com.example.veikko.weathergetter;

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
        public void sendResults(String result);
    }

    private String data = "";

    private NetworkInterfaceCallback callback;

    private String givenURL;

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
            URL url = new URL(givenURL);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

            // httpConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            Log.d("AAA", "intrat");

            String line = reader.readLine();
            while(line != null)
            {
                data += line + "\n";
                line = reader.readLine();

                // System.out.println(line);
            }

            reader.close();

            callback.sendResults(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
