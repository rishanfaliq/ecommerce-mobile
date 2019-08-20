package com.example.rishan.rishan.Models;

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

import com.example.rishan.rishan.Fragments.ListViewObserver;
import com.example.rishan.rishan.Fragments.ViewCart;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rishan on 5/16/2018.
 */


public class CartAdapter extends ArrayAdapter<Cart> {
    Cart cart;
    int totalQuantity;
    double totalPrice;
    ListViewObserver observer;
    String id;
    Context cxt;
    ViewCart viewCart = new ViewCart();

    public CartAdapter(@NonNull Context context, @NonNull List<Cart> objects) {
        super(context, R.layout.cart_item, objects);
    }

    public CartAdapter(@NonNull Context context, @NonNull List<Cart> objects, ListViewObserver observer) {
        super(context, R.layout.cart_item, objects);
        this.cxt = context;
        this.observer = observer;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");

        final User userData = SugarRecord.findById(User.class, Long.parseLong(id));
        final PurchasedItems pi = new PurchasedItems();
        final Date date = Calendar.getInstance().getTime();


        final Cart c = getItem(position);

        TextView productName = view.findViewById(R.id.cartText);
        TextView productPrice = view.findViewById(R.id.cartPriceText);
        TextView productQuantity = view.findViewById(R.id.cartQuantity);
        ImageView productPhoto = view.findViewById(R.id.cartImage);
        Button removeButton = view.findViewById(R.id.removeButton);


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setStatus("Removed");
                c.save();
                remove(c);
                observer.cartChange(c);
                Toast.makeText(getContext(), c.getP().getName() + " removed!", Toast.LENGTH_SHORT).show();
            }
        });



        try {
            productName.setText(c.getP().getName());
            productPrice.setText(String.valueOf(c.getTotal()));
            productQuantity.setText(String.valueOf(c.getNumberOfItems()));

            Picasso.get()
                    .load(c.getP().getScaledImage())
                    .placeholder(R.drawable.dress)
                    .resize(200, 300)
                    .into(productPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;

    }

}