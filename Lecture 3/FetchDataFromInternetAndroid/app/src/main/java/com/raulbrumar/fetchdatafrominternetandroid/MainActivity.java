package com.raulbrumar.fetchdatafrominternetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkThread.NetworkInterfaceCallback
{
    private EditText urlText;
    private TextView plainText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views
        urlText = (EditText)findViewById(R.id.urlText);
        plainText = (TextView)findViewById(R.id.textView);
    }

    public void buttonPressed(View view)
    {
        String url = urlText.getText().toString();

        // "http://oamk.fi/~vetapani/"

        // create and start the thread
        NetworkThread nt = new NetworkThread(this, url);
        nt.start();
    }

    @Override
    public void sendResults(final ArrayList<String> result)
    {
        // update the textbox on the UI thread
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                // plainText.setText(result.get(0));
                for(int i = 0; i < result.size(); i++)
                {
                    plainText.append(result.get(i) + "\n");
                }
            }
        });
    }
}
