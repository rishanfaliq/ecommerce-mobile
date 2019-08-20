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
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rishan on 5/26/2018.
 */

public class BuyFragment extends AppCompatDialogFragment {


    Button confirm;
    NumberPicker quantity;
    int purchaseItem = 0;
    Long position;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
         position = bundle.getLong("cid");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_buy_pop_up, null);
        quantity = view.findViewById(R.id.quantityPicker);
         Cart cart = Cart.findById(Cart.class, position);
        try {
            quantity.setMaxValue(cart.getNumberOfItems());
            quantity.setMinValue(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setView(view)
                .setTitle("Step 1: Select Quantity")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int quantityProducts = quantity.getValue();
                        long productId = position;
                        calculateTotal(quantityProducts, productId);

                        CardDetailsFragment cardDetailsFragment=new CardDetailsFragment();
                        Bundle bundle=new Bundle();
                        bundle.putInt("productQuantity", quantityProducts);
                        bundle.putLong("cid", position);
                        cardDetailsFragment.setArguments(bundle);
                        cardDetailsFragment.show(getFragmentManager(), "CardDetailsFragment");



                    }
                });

        return builder.create();

    }

    double buyPrice;
    double buyTotal;
    double quantityDouble;
    Cart cart= new Cart();
    List<PurchasedItems> purchasedItems = PurchasedItems.listAll(PurchasedItems.class);

    public void calculateTotal(int quantity, long position) {

        PurchasedItems purchasedItem = new PurchasedItems();
        List<Cart> cartList = Cart.listAll(Cart.class);

        try {
            for(Cart c : cartList){
                if (c.getP().getId().equals(position)) {
                    cart=c;


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        cart = Cart.findById(Cart.class, position);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("uid", "");

        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));
        Date date= Calendar.getInstance().getTime();

        try {
            buyPrice =cart.getP().getPrice();
            quantityDouble = quantity;
            buyTotal = buyPrice * quantityDouble;


        purchasedItem.setC(cart);
            purchasedItem.setDate(date.toString());
            purchasedItem.setNumberOfItems(quantity);
            purchasedItem.getC().setTotal(buyTotal);
            purchasedItem.getC().setU(userData);
            purchasedItem.save();
            cart.getP().setQuantity(cart.getP().getQuantity()-quantity);
            if(cart.getNumberOfItems()==0){
                cart.delete();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


      //  }

    }
}

