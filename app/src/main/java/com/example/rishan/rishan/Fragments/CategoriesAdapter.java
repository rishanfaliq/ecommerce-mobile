package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rishan.rishan.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Rishan on 6/10/2018.
 */

public class CategoriesAdapter extends ArrayAdapter<String> {
    public CategoriesAdapter(@NonNull Context context,  @NonNull String[] objects) {
        super(context, R.layout.custom_grid_tab , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater Inf1 = LayoutInflater.from(getContext());
        View customeView = Inf1.inflate(R.layout.custom_grid_tab,parent, false);

        String ClotheItem = getItem(position);
        TextView MainText1 =(TextView) customeView.findViewById(R.id.categoriesText);
        ImageView MainImg1 = (ImageView) customeView.findViewById(R.id.categoriesImage);

        MainText1.setText(ClotheItem);
        if( ClotheItem.equals("Men")) {
            Picasso.get()
                    .load("https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                    .placeholder(R.drawable.dress)
                    .resize(150, 150)
                    .into(MainImg1);
        }
        else if( ClotheItem.equals("Women")) {
            Picasso.get()
                    .load("https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                    .placeholder(R.drawable.dress)
                    .resize(150, 150)
                    .into(MainImg1);        }
        else if( ClotheItem.equals("Children")) {
            Picasso.get()
                    .load("https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                    .placeholder(R.drawable.dress)
                    .resize(150, 150)
                    .into(MainImg1);        }
        else if( ClotheItem.equals("Accessories")) {
            Picasso.get()
                    .load("https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940")
                    .placeholder(R.drawable.dress)
                    .resize(150, 150)
                    .into(MainImg1);        }


        return customeView;
    }
}
