package com.example.rishan.rishan.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.CartAdapter;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rishan on 5/28/2018.
 */

public class CheckoutDialogFragment extends AppCompatDialogFragment {

    Double price;
    int quantity;
    String id;
    Products p;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final PurchasedItems pi;
        Bundle bundle = getArguments();

        try {
            price= bundle.getDouble("price");
            quantity= bundle.getInt("quantity");
        } catch (Exception e) {
            e.printStackTrace();
        }


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.checkout_card_details, null);
        TextView quantityText= view.findViewById(R.id.checkoutQuantityText);
        GridView checkoutItems= view.findViewById(R.id.checkoutGrid);
        TextView priceText= view.findViewById(R.id.checkoutTotalText);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("uid","");
        List<Cart> userCart = Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");

        CheckoutCartAdapter cca=new CheckoutCartAdapter(getContext(), userCart);
        checkoutItems.setAdapter(cca);

        final User userData = User.findById(User.class, Long.parseLong(id));

        try {
            quantityText.setText(String.valueOf(quantity));
            priceText.setText("$ "+ String.valueOf(price));
        } catch (Exception e) {
            e.printStackTrace();
        }


        builder.setView( view)
                .setTitle("Confirm Purchase")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Date d = Calendar.getInstance().getTime();

                        final List<Cart> userCart = Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");
                           for (Cart c : userCart) {
                               PurchasedItems pi=new PurchasedItems();
                               pi.setC(c);
                               pi.setU(userData);
                               pi.setDate(d.toString());
                               pi.setTotal(c.getTotal());
                               pi.setNumberOfItems(c.getNumberOfItems());
                               pi.save();
                               p=Products.findById(Products.class, c.getP().getId());
                               p.setQuantity(c.getP().getQuantity()-c.getNumberOfItems());
                               p.save();
                               c.setStatus("Purchased");
                               c.save();
                           }


                      List<PurchasedItems> a =   PurchasedItems.listAll(PurchasedItems.class);



                        Toast.makeText(getActivity(), quantity+" Products Purchased!", Toast.LENGTH_LONG).show();
                        Fragment fragment= new ViewCart();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, fragment);
                        ft.commit();
                    }
                });

        return builder.create();

    }


}
