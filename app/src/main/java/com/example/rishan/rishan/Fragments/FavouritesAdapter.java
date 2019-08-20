package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rishan.rishan.Models.Favourites;
import com.example.rishan.rishan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 6/6/2018.
 */

public class FavouritesAdapter extends ArrayAdapter<Favourites> {

    public FavouritesAdapter(@NonNull Context context, @NonNull List<Favourites> objects) {
        super(context, R.layout.favourites_custom, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       Favourites f=getItem(position);
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View view=layoutInflater.inflate(R.layout.favourites_custom, parent, false);

        ImageView favouritesImage=view.findViewById(R.id.favouritesImage);
        TextView favouritesPrice=view.findViewById(R.id.favouritesPrice);
        TextView favouritesName=view.findViewById(R.id.favouritesName);

        favouritesPrice.setText(String.valueOf(f.getP().getPrice()));
        favouritesName.setText(f.getP().getName());

        //use shared preferences to set image view photo to comment

        Picasso.get()
                .load(f.getP().getScaledImage())
                .placeholder(R.drawable.ic_launcher)
                .into(favouritesImage);

        return view;


    }
}
