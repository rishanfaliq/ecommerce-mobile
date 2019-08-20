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

import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.R;

import java.util.List;

/**
 * Created by Rishan on 5/26/2018.
 */

public class ReceiptFragment extends Fragment {
String id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.purchased_receipt, container, false);

        ListView listView=view.findViewById(R.id.receiptList);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("uid","");

        List<PurchasedItems> piList=PurchasedItems.listAll(PurchasedItems.class);

        ReceiptAdapter receiptAdapter=new ReceiptAdapter(getContext(), piList);
        listView.setAdapter(receiptAdapter);



        return view;

    }
}
