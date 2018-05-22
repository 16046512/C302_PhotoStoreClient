package com.example.a16046512.c302_photostoreclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private ArrayList<Category> category;
    private Context context;
    private TextView tvName,tvDescription;

    public CategoryAdapter(Context context, int resource, ArrayList<Category> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        category = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.categoryrow, parent, false);

        tvName = (TextView) rowView.findViewById(R.id.tvname);
        tvDescription = (TextView) rowView.findViewById(R.id.tvdescription);


        Category currentCategory = category.get(position);
        // Set the TextView to show the food

        tvName.setText(currentCategory.getName());
        tvDescription.setText(currentCategory.getDescription());
        return rowView;
    }
}

