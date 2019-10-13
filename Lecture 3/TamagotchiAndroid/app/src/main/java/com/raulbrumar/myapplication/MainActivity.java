package com.raulbrumar.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Tamagotchi.TamagotchiCallBack
{
    private int noOfTamagotchies = 5;

    private ArrayList<Tamagotchi> tamagotchies = new ArrayList<Tamagotchi>(noOfTamagotchies);

    private ArrayList<String> titles = new ArrayList<String>(noOfTamagotchies);

    private ArrayList<String> foodsLeft = new ArrayList<String>(noOfTamagotchies);

    private int givenFood = 10;

    private ListView listView;

    private ArrayAdapter adapter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create tamagotchi threads and titles and foodsLeft arrayLists
        for(int i = 0; i < noOfTamagotchies; i++)
        {
            tamagotchies.add(new Tamagotchi(i, 20, 1000, this));
            tamagotchies.get(i).start();

            titles.add("Tamagotchi " + i + ": ALIVE");
            foodsLeft.add(new String("Food Left: " + 20));
        }

        // get the views
        textView =(TextView)findViewById(R.id.textView);
        textView.setText("Hello! Feed your Tamagotchies!");

        listView=(ListView)findViewById(R.id.listView);

        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, foodsLeft);

        // create and set the adapter and listView click listeners
        adapter = new CustomAdapter(this, titles, foodsLeft);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("AAA", "onItemClick: Feed Tamagotchi " + position);
                if (tamagotchies.get(position).feed(givenFood))
                    textView.setText("Tamagotchi " + position + " fed.");
                else
                    textView.setText("Tamagotchi " + position + " is dead. Can't feed.");
            }
        });
    }

    // callback from tamagotchi threads which updates the status on the UI thread
    @Override
    public void tamagotchiStatusChanged(final int index, int food)
    {
        if (food > 20 || food < 0)
            titles.set(index, "Tamagotchi " + index + ": DEAD");
        else
            titles.set(index, "Tamagotchi " + index + ": ALIVE");

        foodsLeft.set(index, "Food: " + food);

        /*
        for(int i = 0; i < noOfTamagotchies; i++)
            System.out.println(i + " : " + foodsLeft.get(i) + "\n");
        */

        Log.d("AAA", index + "::::::::::::::::::::::::::::::::::::::::::::::::::::::" + food);

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                adapter.notifyDataSetChanged();
            }
        });

        boolean someAlive = false;
        for(Tamagotchi t : tamagotchies)
        {
            if (t.getAlive())
            {
                someAlive = true;
                break;
            }
        }

        Log.d("AAA", someAlive + "");

        if (!someAlive)
        {
            Intent intent = new Intent(this, GameOverActivity.class);
            startActivity(intent);
        }
    }

    // custom adapter for this specific UI
    class CustomAdapter extends ArrayAdapter<String>
    {
        Context contex;
        ArrayList<String> titlesArray;
        ArrayList<String> descriptionsArray;

        CustomAdapter(Context context, ArrayList<String> title, ArrayList<String> description) {
            super(context, R.layout.list_view_row, R.id.textView1, title);
            this.contex = context;
            this.titlesArray = title;
            this.descriptionsArray = description;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_view_row, parent, false);
            row.setBackgroundColor(Color.GREEN);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);


            myTitle.setText(titlesArray.get(position));
            myDescription.setText(descriptionsArray.get(position));

            if (titlesArray.get(position).contains("DEAD"))
                row.setBackgroundColor(Color.RED);
            else
                row.setBackgroundColor(Color.GREEN);

            return row;
        }
    }
}
