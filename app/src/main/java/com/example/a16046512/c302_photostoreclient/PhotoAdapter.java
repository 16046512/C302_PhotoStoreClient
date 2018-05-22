package com.example.a16046512.c302_photostoreclient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter<Photo> {
    private ArrayList<Photo> photo;
    private Context context;
    private TextView tvTitle,tvPhotoDescription;

    public PhotoAdapter(Context context, int resource, ArrayList<Photo> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        photo = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.photorow, parent, false);

        tvTitle = (TextView) rowView.findViewById(R.id.tvtitle);
        tvPhotoDescription = (TextView) rowView.findViewById(R.id.tvphotodescription);


        Photo currentPhoto = photo.get(position);
        // Set the TextView to show the food
        Log.i("ttt",currentPhoto.getTitle());
        tvTitle.setText(currentPhoto.getTitle());
        tvPhotoDescription.setText(currentPhoto.getDescription());
        return rowView;
    }

}
