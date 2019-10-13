package com.raulbrumar.flickrviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<String> imageLinks;

    // API
    private final String baseLink = "https://api.flickr.com/services/feeds/photos_public.gne?nojsoncallback=?&format=json&tags=";

    ListView listView;

    EditText editText;

    RequestQueue requestQueue;

    ImageListViewAdapter adapter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get views
        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);

        requestQueue = Volley.newRequestQueue(this);

        imageLinks = new ArrayList<String>();

        // set adapter
        adapter = new ImageListViewAdapter(this, imageLinks);

        listView.setAdapter(adapter);

        context = this;

        // set onClick listener to open the selected photo with the photo editor
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("AAA", "onItemClick " + position);

                Intent dsPhotoEditorIntent = new Intent(context, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(Uri.parse(imageLinks.get(position)));

                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_PIXELATE, DsPhotoEditorActivity.TOOL_ORIENTATION};
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);

                startActivityForResult(dsPhotoEditorIntent, 200);
            }
        });
    }

    public void onClick(View view)
    {
        // clear old image links array
        imageLinks.clear();

        // format the tags for the API
        String link = baseLink + editText.getText().toString().replace(", ", ",").replace(" ", ",");

        Log.d("AAA", "onClick: " + link);

        // get the image links with volley and parse the JSONs
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, link, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("items");

                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject picture = jsonArray.getJSONObject(i);

                                JSONObject media = picture.getJSONObject("media");
                                String pictureLink = media.getString("m");

                                imageLinks.add(pictureLink);
                                Log.d("AAA", "onResponse: " + pictureLink + "     " + i);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse: ERROR");
                    }});

        requestQueue.add(jsonObjectRequest);
    }
}
