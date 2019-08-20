package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 6/6/2018.
 */

public class CheckoutCartAdapter extends ArrayAdapter<Cart> {
    public CheckoutCartAdapter(@NonNull Context context,   @NonNull List<Cart> objects) {
        super(context,  R.layout.checkout_cart_custom, objects);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());;
        View view = layoutInflater.inflate(R.layout.checkout_cart_custom, parent, false);
        //   Products product= products.get(position);
        Cart c=getItem(position);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        TextView gridText = view.findViewById(R.id.checkoutGridText);
        ImageView gridPic = view.findViewById(R.id.checkoutGridPic);

        try {
            gridText.setText(c.getP().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Picasso.get()
                    .load(c.getP().getScaledImage())
                    .placeholder(R.drawable.dress)
                    .resize(100, 100)
                    .into(gridPic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
