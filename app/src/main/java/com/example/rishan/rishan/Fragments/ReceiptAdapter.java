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

import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 5/26/2018.
 */

public class ReceiptAdapter extends ArrayAdapter<PurchasedItems> {
    PurchasedItems pi;
    String id;
    public ReceiptAdapter(@NonNull Context context, @NonNull List<PurchasedItems> objects) {
        super(context, R.layout.purchase_histrory_custom , objects);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater=LayoutInflater.from(getContext());;
        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        //   Products product= products.get(position);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");
        pi=getItem(position);
        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));


        TextView receiptName = view.findViewById(R.id.receiptName);
        TextView receiptQuantity = view.findViewById(R.id.receiptQuantity);
        TextView receiptDate = view.findViewById(R.id.receiptDate);
        ImageView receiptImage = view.findViewById(R.id.receiptImage);

        receiptName.setText(pi.getC().getP().getName());
        receiptQuantity.setText(pi.getNumberOfItems());
        receiptDate.setText(pi.getDate());
        Picasso.get()
                .load(pi.getC().getP().getScaledImage())
                .placeholder(R.drawable.dress)
                .into(receiptImage);






        return view;

    }
}
