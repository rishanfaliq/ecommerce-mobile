package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.CartAdapter;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 5/24/2018.
 */

public class PurchaseHistoryFragment extends Fragment {

    String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater = LayoutInflater.from(getContext());
        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.purchase_history, container, false);
        getActivity().setTitle("Orders");


        ListView listView = view.findViewById(R.id.purchaseList);
        final List<Cart> CartListView = new ArrayList<>();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");

        List<PurchasedItems> piList =  PurchasedItems.find(PurchasedItems.class, "u = ? and status = ?", id , "Purchased");
        PurchaseHistoryAdapter piAdapter = new PurchaseHistoryAdapter(getContext(), piList);
        listView.setAdapter(piAdapter);



        return view;

    }
}