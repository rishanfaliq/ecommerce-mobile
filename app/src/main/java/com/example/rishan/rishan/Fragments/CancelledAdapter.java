package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 6/9/2018.
 */


public class CancelledAdapter extends ArrayAdapter<PurchasedItems> {
    PurchasedItems pi;
    String id;

    public CancelledAdapter(@NonNull Context context, @NonNull List<PurchasedItems> objects) {
        super(context, R.layout.cancelled_custom , objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(getContext());;
        View view = inflater.inflate(R.layout.cancelled_custom, parent, false);

        TextView purchaseProduct = view.findViewById(R.id.purchaseProductCancel);
        TextView purchasePrice = view.findViewById(R.id.purchasePriceCancel);
        TextView purchaseQuantity = view.findViewById(R.id.purchaseQuantityCancel);
        ImageView purchaseImage = view.findViewById(R.id.purchaseImageCancel);

        //Button cartBuy = view.findViewById(R.id.cartBuy);

        pi=getItem(position);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        //List<PurchasedItems> userCart = PurchasedItems.find(PurchasedItems.class, "u = ? and status = ?", id , "Purchased");


        try {
            purchaseProduct.setText(pi.getC().getP().getName());
            purchasePrice.setText(String.valueOf(pi.getC().getP().getPrice()));
            purchaseQuantity.setText(String.valueOf(pi.getC().getNumberOfItems()));

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Picasso.get()
                    .load(pi.getC().getP().getScaledImage())
                    .placeholder(R.drawable.dress)
                    .resize(200, 300)
                    .into(purchaseImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;

    }
}
