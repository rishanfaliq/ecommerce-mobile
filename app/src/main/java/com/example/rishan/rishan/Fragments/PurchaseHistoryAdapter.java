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

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 5/27/2018.
 */

public class PurchaseHistoryAdapter extends ArrayAdapter<PurchasedItems> {

String id;
    PurchasedItems specificPi;

    public PurchaseHistoryAdapter(@NonNull Context context,  @NonNull List<PurchasedItems> objects) {
        super(context, R.layout.purchase_histrory_custom , objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(getContext());;
        View view = inflater.inflate(R.layout.purchase_histrory_custom, parent, false);

        TextView purchaseProduct = view.findViewById(R.id.purchaseProduct);
        TextView purchasePrice = view.findViewById(R.id.purchasePrice);
        TextView purchaseQuantity = view.findViewById(R.id.purchaseQuantity);
        ImageView purchaseImage = view.findViewById(R.id.purchaseImage);
        TextView purchaseTime = view.findViewById(R.id.purchaseTime);
        Button cancelOrder = view.findViewById(R.id.purchaseCancel);


        //Button cartBuy = view.findViewById(R.id.cartBuy);

        final PurchasedItems pi=getItem(position);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        //List<PurchasedItems> userCart = PurchasedItems.find(PurchasedItems.class, "u = ? and status = ?", id , "Purchased");
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pi.setStatus("Cancelled");
                    pi.save();

                    Products p=Products.findById(Products.class, pi.getC().getP().getId());
                    p.setQuantity(p.getQuantity()+pi.getC().getNumberOfItems());

                    Toast.makeText(getContext(), "Order Cancelled", Toast.LENGTH_SHORT).show();
                    p.save();
                remove(pi);

            }
        });

        try {
            purchaseProduct.setText(pi.getC().getP().getName());
            purchasePrice.setText(String.valueOf(pi.getC().getP().getPrice()));
            purchaseQuantity.setText(String.valueOf(pi.getC().getNumberOfItems()));
            purchaseTime.setText(pi.getDate());
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
