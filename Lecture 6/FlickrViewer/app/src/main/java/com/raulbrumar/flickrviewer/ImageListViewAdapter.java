package com.raulbrumar.flickrviewer;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageListViewAdapter extends ArrayAdapter<String>
{
    Context context;
    ArrayList<String> imageLinks;

    ImageListViewAdapter(Context context, ArrayList<String> imageLinks) {
        super(context, R.layout.list_view_row, imageLinks);
        this.context = context;
        this.imageLinks = imageLinks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_view_row, parent, false);

        ImageView imageView = row.findViewById(R.id.imageView);
        TextView textView = row.findViewById(R.id.textView);

        textView.append(imageLinks.get(position));

        // add picasoooo ti-am dat bip
        // si sunt voinic

        // Log.d("AAA", "getView: " + imageLinks.get(position));
        Picasso.get().load(imageLinks.get(position)).into(imageView);

        return row;
    }
}
