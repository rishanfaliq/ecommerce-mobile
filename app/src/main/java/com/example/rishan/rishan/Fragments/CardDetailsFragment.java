package com.example.rishan.rishan.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rishan on 5/24/2018.
 */

public class CardDetailsFragment extends AppCompatDialogFragment {

    Long position;
    int quantityProducts;

    //OnCreateDialog for CardDetailsFragment
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final PurchasedItems pi=new PurchasedItems();
        Bundle bundle = getArguments();

        position= bundle.getLong("cid");
        quantityProducts= bundle.getInt("productQuantity");

        //Bundle bundle=getArguments();
        //final Long position=bundle.getLong("uid");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        final String id = sharedPreferences.getString("uid", "");
        final Products p=Products.findById(Products.class, position);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_card_details, null);

        TextView itemName=view.findViewById(R.id.itemName);
        ImageView itemImage=view.findViewById(R.id.itemImage);

        try {
            itemName.setText(p.getName());
            Picasso.get()
                    .load(p.getScaledImage())
                    .placeholder(R.drawable.dress)
                    .into(itemImage);
        } catch (Exception e) {
            e.printStackTrace();
        }


        builder.setView( view)
                .setTitle("Step 2: Enter Card Details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })

            .setPositiveButton("Confirm ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Date d = Calendar.getInstance().getTime();
                final List<Cart> userCart = Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");
                for(Cart c:userCart){
                    if(c.getId().equals(position)){

                        c.setStatus("Purchased");
                        c.setNumberOfItems(0);
                        c.getP().setQuantity(c.getP().getQuantity()-quantityProducts);
                        pi.setC(c);
                        pi.setDate(d.toString());
                        pi.save();
                        c.save();
                       /* if(c.getNumberOfItems()==0) {
                            c.delete();
                        }*/
                    }


                }

                Toast.makeText(getActivity(), "Purchase Completed!", Toast.LENGTH_LONG).show();
            }
            });

        return builder.create();

    }



}
