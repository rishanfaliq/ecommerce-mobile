package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.CartAdapter;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 5/30/2018.
 */

public class CancelledCart extends Fragment {
    String id;
    int finalQuantity=0;
    double finalPrice=0.0;

    TextView totalPrice;
    TextView totalQuantity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.view_cart, container, false);


        TextView cartDetails=view.findViewById(R.id.cartText);
        ListView listView=view.findViewById(R.id.cartList);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("uid","");

        User userData = User.findById(User.class, Long.parseLong(id));

        final List<Cart> userCart = Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");
        //List<Cart> bb = Cart.findWithQuery(Cart.class, "Select * from Ex_Records where ex_recordId = ?", "ex0001");


       // CartAdapter cartAdapter = new CartAdapter(getContext(), userCart);
      //  listView.setAdapter(cartAdapter);


        return view;

    }


}
