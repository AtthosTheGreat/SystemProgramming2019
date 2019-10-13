package com.raulbrumar.dailylunchmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    public static final String firstHalfAPI = "https://www.amica.fi/api/restaurant/menu/day?date=";
    // date format should be: YYYY-MM-DD
    public static final String lastHalfAPI = "&language=en&restaurantPageId=66287";

    private TextView selectedDayTextView;
    private Button prevDayButton;
    private Button nextDayButton;
    private ListView menuListView;

    private Date selectedDate;
    private SimpleDateFormat displayDateFormatter;
    private SimpleDateFormat apiDateFormatter;

    RequestQueue requestQueue;

    private ArrayList<String> foodsList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views
        selectedDayTextView = findViewById(R.id.selectedDayTextView);
        prevDayButton = findViewById(R.id.prevDayButton);
        nextDayButton = findViewById(R.id.nextDayButton);
        menuListView = findViewById(R.id.menuListView);

        requestQueue = Volley.newRequestQueue(this);

        // set up the arrayList and the adapter
        foodsList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodsList);
        menuListView.setAdapter(adapter);

        // set up the formatters
        displayDateFormatter = new SimpleDateFormat("dd-MM-YYYY");
        apiDateFormatter = new SimpleDateFormat("YYYY-MM-dd");

        // update the UI for the first time
        updateSelectedDateAndListView(new Date().getTime());
    }

    private void updateSelectedDateAndListView(long date)
    {
        // update date textView
        selectedDate = new Date(date);
        selectedDayTextView.setText(displayDateFormatter.format(selectedDate));

        // updateListView
        String link = firstHalfAPI + apiDateFormatter.format(selectedDate) + lastHalfAPI;
        Log.d("AAA", "updateSelectedDateAndListView: " + link);

        // clear old foods array
        foodsList.clear();

        // get new foods as a JSON and parse them to get the required info
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, link, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            // Log.d("AAA", response.getJSONObject("LunchMenu").toString());

                            JSONObject lunchMenu = response.getJSONObject("LunchMenu");
                            JSONArray setMenus = lunchMenu.getJSONArray("SetMenus");

                            if (setMenus.length() == 0)
                            {
                                foodsList.add("No meal found");
                            }
                            else
                            {
                                for (int i = 0; i < setMenus.length(); i++)
                                {
                                    JSONObject menu = setMenus.getJSONObject(i);

                                    JSONArray mealsArray = menu.getJSONArray("Meals");
                                    for (int j = 0; j < mealsArray.length(); j++)
                                    {
                                        JSONObject meal = mealsArray.getJSONObject(j);

                                        String name = meal.getString("Name");
                                        foodsList.add(name);

                                        // int recipeID = meal.getInt("RecipeId");
                                        Log.d("AAA", name);
                                    }
                                }
                            }
                            // notify adapter
                            adapter.notifyDataSetChanged();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();

                            foodsList.add("No meal found");
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse: ERROR");
                    }});

        requestQueue.add(jsonObjectRequest);
    }

    public void onPrevDayButtonClick(View view)
    {
        updateSelectedDateAndListView(selectedDate.getTime()-24*60*60*1000);
    }

    public void onNextDayButtonClick(View view)
    {
        updateSelectedDateAndListView(selectedDate.getTime()+24*60*60*1000);
    }
}
