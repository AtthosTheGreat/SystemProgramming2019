package com.raulbrumar.publishsubscribe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView partsTextView;

    private TextView carNoTextView;

    private Button button;

    private int noOfCars = 0;

    private boolean listenEverything = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views
        partsTextView = findViewById(R.id.carPartsTextView);
        carNoTextView = findViewById(R.id.carsMadeTextView);
        button = findViewById(R.id.button);

        // set the receivers with the required filters
        LocalBroadcastManager.getInstance(this).registerReceiver(partMessageReceiver, new IntentFilter("car_part"));
        LocalBroadcastManager.getInstance(this).registerReceiver(carMessageReceiver, new IntentFilter("car_done"));
    }

    // this method will toggle between listening to car progress or not
    public void onClick(View view)
    {
        if (listenEverything)
        {
            button.setText("ENABLE PROGRESS");
            partsTextView.setText("");
            LocalBroadcastManager.getInstance(this).unregisterReceiver(partMessageReceiver);
            listenEverything = false;
        }
        else
        {
            button.setText("DISABLE PROGRESS");
            LocalBroadcastManager.getInstance(this).registerReceiver(partMessageReceiver, new IntentFilter("car_part"));
            listenEverything = true;
        }
    }

    // updates the parts when a new part has been made
    private BroadcastReceiver partMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String part = intent.getStringExtra("part");

            partsTextView.append(part + "\n");
        }
    };

    // updates the number of produced cars when a car has been made
    private BroadcastReceiver carMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            partsTextView.setText("");
            noOfCars++;
            carNoTextView.setText("Cars made: " + noOfCars);
        }
    };
}
