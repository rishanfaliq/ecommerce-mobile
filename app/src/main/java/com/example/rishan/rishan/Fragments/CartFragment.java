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
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Rishan on 5/23/2018.
 */

public class CartFragment extends AppCompatDialogFragment {

    Button confirm;

    int cartItem = 0;

    Long position;
    CardDetailsFragment cardDetailsFragment;

    double buyPrice;
    double buyTotal;
    double quantityDouble;
    Cart cart;
    List<Cart> cartList = Cart.listAll(Cart.class);


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = null;
        View view = null;


        Bundle bundle = getArguments();
        position = bundle.getLong("pid");
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.activity_buy_pop_up, null);
        final NumberPicker quantity = view.findViewById(R.id.quantityPicker);
        try {
            Products products = new Products();
            products = Products.findById(Products.class, position);
            quantity.setMaxValue(products.getQuantity());
            quantity.setMinValue(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setView(view)
                .setTitle("Select Quantity")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int quantityProducts = quantity.getValue();
                        long productId = position;
                        calculateTotal(quantityProducts, productId);
                        Toast.makeText(getActivity(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();

    }


    public void calculateTotal(int quantity, long position) {


        Products p = Products.findById(Products.class, position);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("uid", "");
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        List<Cart> carts =  Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");

        try {

            if (carts.size() >= 1)
            {
                for (Cart c : carts)
                {
                    if (c.getP().getId().equals(position))
                    {
                        cart = c;
                        break;

                    }
                    else
                    {
                        cart = new Cart();
                    }
                }
            }
            else
                {
                cart = new Cart();
                }


            if (cart.getNumberOfItems() >= 1) {
                if (cart.getNumberOfItems() >= cart.getP().getQuantity()) {
                    Toast.makeText(getActivity(),"Cant add anymore!", Toast.LENGTH_SHORT).show();
                    cart.setNumberOfItems(cart.getP().getQuantity());
                    cart.setTotal(cart.getP().getQuantity() * p.getPrice());
                    cart.setStatus("Pending");
                    cart.save();

                }
                else
                {
                    cartItem = cart.getNumberOfItems();
                    cartItem = cartItem + quantity;
                    cart.setNumberOfItems(cartItem);
                    cart.setTotal(cartItem * p.getPrice());
                    cart.setStatus("Pending");
                    cart.save();
                }
                }
                else
                {
                buyPrice = p.getPrice();
                quantityDouble = quantity;
                buyTotal = buyPrice * quantityDouble;
                cart.setP(p);
                cart.setStatus("Pending");
                cart.setNumberOfItems(quantity);
                cart.setTotal(buyTotal);
                cart.setU(userData);
                cart.save();
                }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



