package com.example.rishan.rishan.Fragments;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.CartAdapter;
import com.example.rishan.rishan.R;
import java.util.List;



public class ViewCart extends Fragment implements ListViewObserver  {
    String id;
    int finalQuantity=0;
    double finalPrice=0.0;
    TextView totalPrice;
    TextView totalQuantity;
    List<Cart> userCart;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflater = LayoutInflater.from(getContext());
         view = inflater.inflate(R.layout.view_cart, container, false);
        ListView listView=view.findViewById(R.id.cartList);
        getActivity().setTitle("Shopping Cart");


        Button checkout=view.findViewById(R.id.checkout);
        totalPrice=view.findViewById(R.id.totalPrice);
        totalQuantity=view.findViewById(R.id.totalQuantity);
        final FragmentManager fm = getActivity().getSupportFragmentManager();

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("uid","");

        userCart = Cart.find(Cart.class, "u = ? and status = ?", id , "Pending");
        CartAdapter cartAdapter = new CartAdapter(getContext(), userCart, this);
        listView.setAdapter(cartAdapter);

        for(Cart c: userCart)
        {
            finalQuantity=finalQuantity+ c.getNumberOfItems();
            finalPrice=finalPrice + c.getTotal();
        }

        totalPrice.setText(String.valueOf(finalPrice));
        totalQuantity.setText(String.valueOf(finalQuantity));

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle bundle=new Bundle();
                bundle.putInt("quantity", finalQuantity);
                bundle.putDouble("price", finalPrice);
                CheckoutDialogFragment checkout=new CheckoutDialogFragment();
                checkout.setArguments(bundle);
                checkout.show(getFragmentManager(), "CheckoutDialogFragment");
            }
        });

         return view;

    }


    @Override
    public void cartChange(Cart c)
    {
        finalPrice=finalPrice-c.getTotal();
        finalQuantity=finalQuantity-c.getNumberOfItems();
        totalPrice.setText(String.valueOf(finalPrice));
        totalQuantity.setText(String.valueOf(finalQuantity));

    }

}
