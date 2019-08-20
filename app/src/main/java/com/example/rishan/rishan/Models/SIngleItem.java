package com.example.rishan.rishan.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 4/16/2018.
 */

public class SIngleItem extends ArrayAdapter<Products> implements Filterable {
    Products products;
    Context aContext;
    ArrayList<Products> productsSearchList;
    List<Products> filterList = Products.listAll(Products.class);
    Filter filter;
String id;
    public SIngleItem(Context aContext, List<Products> objects) {
        super(aContext, R.layout.custom_layout, objects);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Products p=getItem(position);
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());


        view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        //   Products product= products.get(position);


        SharedPreferences sharedPreferences= getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("uid","");

        TextView textView =  view.findViewById(R.id.productText);
        TextView priceText = view.findViewById(R.id.priceText);
        TextView descriptionText = view.findViewById(R.id.descriptionText);
        ImageView gridFav = view.findViewById(R.id.gridFav);

        final List<Favourites> favList =  Favourites.find(Favourites.class, "u = ? ", id);


        for (Favourites f : favList) {
            if (f.getP().getId().equals(p.getId())) {
                gridFav.setVisibility(View.VISIBLE);

                gridFav.setBackgroundResource(R.drawable.ye1);

            }
        }

        //ToggleButton favouritesGrid = view.findViewById(R.id.favouritesGrid);
        ImageView imageViewPhoto =  view.findViewById(R.id.productIcon);
        List<Products> pListNew=Products.listAll(Products.class);
        String x=String.valueOf(p.getPrice());
        textView.setText(p.getName());
        priceText.setText("$"+x);
        descriptionText.setText("Available :" +pListNew.get(position).getQuantity()+" items");





        Picasso.get()
                .load(p.getScaledImage())
                .placeholder(R.drawable.dress)
                .resize(150,150)
                .into(imageViewPhoto);




        return view;

    }

}
